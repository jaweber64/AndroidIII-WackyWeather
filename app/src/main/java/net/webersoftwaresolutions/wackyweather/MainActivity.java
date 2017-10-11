package net.webersoftwaresolutions.wackyweather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JSONTask().execute("https://api.darksky.net/forecast/b7f8823f23afca0441b3119d219d30a7/42.212869,-88.236382");

    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL darkSky = new URL(params[0]);
                connection = (HttpURLConnection) darkSky.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONObject dailyObject = parentObject.getJSONObject("daily");
                String fiveDsum = dailyObject.getString("summary");

                JSONArray dailyArray = dailyObject.getJSONArray("data");
                for (int i = 0; i < dailyArray.length(); i++) {
                    JSONObject dayObject = dailyArray.getJSONObject(i);
                    String dayHtemp = dayObject.getString("temperatureHigh");
                    //System.out.print("High temp, day " + i + ": " + dayHtemp);
                }

                return dailyObject.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //System.out.println("onPostExecute");
            //System.out.println("result: " + result);
            TextView Htemp1 = (TextView) findViewById(R.id.Htemp);
            TextView Ltemp1 = (TextView) findViewById(R.id.Ltemp);
            TextView DP1 = (TextView) findViewById(R.id.DewPt);
            TextView Precip1 = (TextView) findViewById(R.id.Precip);
            TextView Sum1 = (TextView) findViewById(R.id.Sum);

            TextView Htemp2 = (TextView) findViewById(R.id.Htemp2);
            TextView Ltemp2 = (TextView) findViewById(R.id.Ltemp2);
            TextView DP2 = (TextView) findViewById(R.id.DewPt2);
            TextView Precip2 = (TextView) findViewById(R.id.Precip2);
            TextView Sum2 = (TextView) findViewById(R.id.sum2);

            TextView day3Htmp = (TextView) findViewById(R.id.day3Htmp);
            TextView day3Ltmp = (TextView) findViewById(R.id.day3Ltmp);
            TextView DP3 = (TextView) findViewById(R.id.day3Dew);
            TextView day3Precip = (TextView) findViewById(R.id.day3Precip);

            TextView day4Htmp = (TextView) findViewById(R.id.day4Htmp);
            TextView day4Ltmp = (TextView) findViewById(R.id.day4Ltmp);
            TextView DP4 = (TextView) findViewById(R.id.day4Dew);
            TextView day4Precip = (TextView) findViewById(R.id.day4Precip);

            TextView day5Htmp = (TextView) findViewById(R.id.day5Htmp);
            TextView day5Ltmp = (TextView) findViewById(R.id.day5Ltmp);
            TextView DP5 = (TextView) findViewById(R.id.day5Dew);
            TextView day5Precip = (TextView) findViewById(R.id.day5Precip);

            TextView day6Htmp = (TextView) findViewById(R.id.day6Htmp);
            TextView day6Ltmp =  (TextView) findViewById(R.id.day6Ltmp);
            TextView DP6 = (TextView) findViewById(R.id.day6Dew);
            TextView day6Precip = (TextView) findViewById(R.id.day6Precip);

            TextView day7Htmp = (TextView) findViewById(R.id.day7Htmp);
            TextView day7Ltmp = (TextView) findViewById(R.id.day7Ltmp);
            TextView DP7 = (TextView) findViewById(R.id.day7Dew);
            TextView day7Precip = (TextView) findViewById(R.id.day7Precip);

            TextView FiveDaySum = (TextView) findViewById(R.id.FiveDaySum);

            String dayHtemp, dayLtemp, dewTemp, precip, sum = "";

            try {
                JSONObject dailyO = new JSONObject(result);
                String fiveDsum = dailyO.getString("summary");
                FiveDaySum.setText(fiveDsum);

                JSONArray dailyArray = dailyO.getJSONArray("data");
                for (int i = 0; i < dailyArray.length(); i++) {
                    JSONObject dayObject = dailyArray.getJSONObject(i);
                    if ((i == 0) || (i == 1)) {
                        dayHtemp = "High: " + dayObject.getString("temperatureHigh");
                        dayLtemp = "Low: " + dayObject.getString("temperatureLow");
                        dewTemp = "Dew Pt: " + dayObject.getString("dewPoint");
                        precip = "Precip %: " + dayObject.getString("precipProbability");
                        sum = dayObject.getString("summary");
                    } else {
                        dayHtemp = dayObject.getString("temperatureHigh");
                        dayLtemp = dayObject.getString("temperatureLow");
                        dewTemp = dayObject.getString("dewPoint");
                        precip = dayObject.getString("precipProbability");
                    }
                    switch (i) {
                        case 0: Htemp1.setText(dayHtemp);
                            Ltemp1.setText(dayLtemp);
                            DP1.setText(dewTemp);
                            Precip1.setText(precip);
                            Sum1.setText(sum); break;
                        case 1: Htemp2.setText(dayHtemp);
                            Ltemp2.setText(dayLtemp);
                            DP2.setText(dewTemp);
                            Precip2.setText(precip);
                            Sum2.setText(sum); break;
                        case 2: day3Htmp.setText(dayHtemp);
                            day3Ltmp.setText(dayLtemp);
                            DP3.setText(dewTemp);
                            day3Precip.setText(precip); break;
                        case 3: day4Htmp.setText(dayHtemp);
                            day4Ltmp.setText(dayLtemp);
                            DP4.setText(dewTemp);
                            day4Precip.setText(precip); break;
                        case 4: day5Htmp.setText(dayHtemp);
                            day5Ltmp.setText(dayLtemp);
                            DP5.setText(dewTemp);
                            day5Precip.setText(precip); break;
                        case 5: day6Htmp.setText(dayHtemp);
                            day6Ltmp.setText(dayLtemp);
                            DP6.setText(dewTemp);
                            day6Precip.setText(precip); break;
                        case 6: day7Htmp.setText(dayHtemp);
                            day7Ltmp.setText(dayLtemp);
                            DP7.setText(dewTemp);
                            day7Precip.setText(precip); break;
                        default: //error
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

