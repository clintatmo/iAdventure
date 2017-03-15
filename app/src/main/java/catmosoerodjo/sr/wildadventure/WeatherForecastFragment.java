package catmosoerodjo.sr.wildadventure;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import catmosoerodjo.sr.wildadventure.dto.OpenWeatherObject;
import catmosoerodjo.sr.wildadventure.util.Constants;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeatherForecastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeatherForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherForecastFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextView weather_response_country;
    private TextView weather_response_place;
    private TextView weather_response_sunrise;
    private TextView weather_response_sunset;
    private ProgressBar progressBar;
    private TextView weather_response_humidity;
    private TextView weather_response_temp;
    private View weather_response_data_layout;

    public WeatherForecastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherForecastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherForecastFragment newInstance(String param1, String param2) {
        WeatherForecastFragment fragment = new WeatherForecastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather_forecast, container,false);
        weather_response_data_layout = view.findViewById(R.id.weather_response_data_layout);
        weather_response_data_layout.setVisibility(View.GONE);
        weather_response_country = (TextView) view.findViewById(R.id.weather_response_country);
        weather_response_place = (TextView) view.findViewById(R.id.weather_response_place);
        weather_response_sunrise = (TextView) view.findViewById(R.id.weather_response_sunrise);
        weather_response_sunset = (TextView) view.findViewById(R.id.weather_response_sunset);
        weather_response_humidity = (TextView) view.findViewById(R.id.weather_response_humidity);
        weather_response_temp = (TextView) view.findViewById(R.id.weather_response_temp);

        progressBar = (ProgressBar) view.findViewById(R.id.weather_response_loader);
        new HttpRequestTask().execute();
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public double fahrenheitToCelsius(double fahrenheit) {
        return Math.round(((fahrenheit - 32) * 5 / 9) * 100.0) / 100.0;
    }

    public String convertTimeStampToTime(double timestamp){
        Date date = new Date((long) timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(date);
    }

    public class HttpRequestTask extends AsyncTask<Object, Object, ResponseEntity<OpenWeatherObject>> {
        @Override
        protected ResponseEntity<OpenWeatherObject> doInBackground(Object... params) {
            try {
                final String url = "http://api.openweathermap.org/data/2.5/weather?lat=5.839398&lon=-55.199089&appid=d0fa46ed8c2e2d079ed1bd2408c2632b";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                headers.add("x-api-key", Constants.OPEN_WEATHER_API_KEY);

                Map<String, Double> parameters = new HashMap<>();
                HttpEntity entity = new HttpEntity(headers);

                return restTemplate.getForEntity(url, OpenWeatherObject.class);

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return new ResponseEntity<OpenWeatherObject>(HttpStatus.BAD_REQUEST);
        }

        @Override
        protected void onPostExecute(ResponseEntity<OpenWeatherObject> obj) {
            progressBar.setVisibility(View.GONE);
            weather_response_data_layout.setVisibility(View.VISIBLE);
            weather_response_country.setText(obj.getBody().getSys().getCountry());
            weather_response_place.setText(obj.getBody().getName());
            weather_response_sunrise.setText(convertTimeStampToTime(obj.getBody().getSys().getSunrise()));
            weather_response_sunset.setText(convertTimeStampToTime(obj.getBody().getSys().getSunset()));
            weather_response_humidity.setText(String.valueOf(obj.getBody().getMain().getHumidity()) + "%");

            weather_response_temp.setText(String.valueOf(fahrenheitToCelsius(obj.getBody().getMain().getTemp())));
        }

    }
}
