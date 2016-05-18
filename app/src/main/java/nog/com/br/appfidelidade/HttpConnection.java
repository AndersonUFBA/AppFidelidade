package nog.com.br.appfidelidade;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class HttpConnection
{
    public HttpConnection()
    {
    }

    private String convertInputStreamToString(InputStream mInputStream) throws IOException
    {
        BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(mInputStream));

        String line = "";
        String result = "";

        while ((line = mBufferedReader.readLine()) != null)
        {
            result += line;
        }

        mInputStream.close();
        return result;
    }

    // ------------------------------ POST Methods ------------------------------ //

    private String POST(String URL, String Authorization, JSONObject sendData)
    {
        URL mUrl = null;
        HttpURLConnection mHttpURLConnection = null;
        InputStream mInputStream = null;
        String getData = "Error";

        try
        {
            mUrl = new URL(URL);
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setUseCaches(false);
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(true);
            mHttpURLConnection.setReadTimeout(15000);
            mHttpURLConnection.setConnectTimeout(15000);
            mHttpURLConnection.setChunkedStreamingMode(1000);
            mHttpURLConnection.setRequestMethod("POST");
            mHttpURLConnection.setRequestProperty("Accept", "application/json");
            mHttpURLConnection.setRequestProperty("Content-Type", "application/json");
            //mHttpURLConnection.setRequestProperty("Authorization", Authorization);
            mHttpURLConnection.connect();

            OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpURLConnection.getOutputStream());
            mOutputStreamWriter.write(sendData.toString());
            mOutputStreamWriter.flush();
            mOutputStreamWriter.close();

            int responseCode = mHttpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                mInputStream = mHttpURLConnection.getInputStream();

                if (mInputStream != null)
                {
                    getData = convertInputStreamToString(mInputStream);
                }
            }
            else
            {
                Log.e("Debug", "Erro ao tentar se conectar - " + String.valueOf(responseCode));
            }
        }
        catch (ProtocolException e)
        {
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (mHttpURLConnection != null)
            {
                mHttpURLConnection.disconnect();
            }
        }

        return getData;
    }

    private String POST(String URL, String Authorization, String XAccessToken, JSONObject sendData)
    {
        URL mUrl = null;
        HttpURLConnection mHttpURLConnection = null;
        InputStream mInputStream = null;
        String getData = "Error";

        try
        {
            mUrl = new URL(URL);
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setUseCaches(false);
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(true);
            mHttpURLConnection.setReadTimeout(15000);
            mHttpURLConnection.setConnectTimeout(15000);
            mHttpURLConnection.setChunkedStreamingMode(1000);
            mHttpURLConnection.setRequestMethod("POST");
            mHttpURLConnection.setRequestProperty("Accept", "application/json");
            mHttpURLConnection.setRequestProperty("Content-Type", "application/json");
            mHttpURLConnection.setRequestProperty("Authorization", Authorization);
            mHttpURLConnection.setRequestProperty("X-Access-Token", XAccessToken);
            mHttpURLConnection.connect();

            OutputStreamWriter mOutputStreamWriter = new OutputStreamWriter(mHttpURLConnection.getOutputStream());
            mOutputStreamWriter.write(sendData.toString());
            mOutputStreamWriter.flush();
            mOutputStreamWriter.close();

            int responseCode = mHttpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                mInputStream = mHttpURLConnection.getInputStream();

                if (mInputStream != null)
                {
                    getData = convertInputStreamToString(mInputStream);
                }
            }
            else
            {
                Log.e("Debug", "Erro ao tentar se conectar - " + String.valueOf(responseCode));
            }
        }
        catch (ProtocolException e)
        {
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (mHttpURLConnection != null)
            {
                mHttpURLConnection.disconnect();
            }
        }

        return getData;
    }

    private class AsyncTaskPOST extends AsyncTask<String, Void, String>
    {
        String URL;
        String Authorization;
        String XAccessToken;
        JSONObject sendData;

        private AsyncTaskPOST()
        {
        }

        private AsyncTaskPOST(String URL, String Authorization, JSONObject sendData)
        {
            this.URL = URL;
            this.Authorization = Authorization;
            this.sendData = sendData;
        }

        private AsyncTaskPOST(String URL, String Authorization, String XAccessToken, JSONObject sendData)
        {
            this.URL = URL;
            this.Authorization = Authorization;
            this.XAccessToken = XAccessToken;
            this.sendData = sendData;
        }

        @Override
        protected String doInBackground(String... params)
        {
            if (XAccessToken == null)
            {
                return POST(URL, Authorization, sendData);
            }
            else
            {
                return POST(URL, Authorization, XAccessToken, sendData);
            }
        }

        @Override
        protected void onPostExecute(String s)
        {
            Log.d("Debug", s);
        }
    }

    public String requestPOST(String URL, String Authorization, JSONObject sendData)
    {
        String data = "Error";

        try
        {
            data = new AsyncTaskPOST(URL, Authorization, sendData).execute().get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        return data;
    }

    public String requestPOST(String URL, String Authorization, String XAccessToken, JSONObject sendData)
    {
        String data = "Error";

        try
        {
            data = new AsyncTaskPOST(URL, Authorization, XAccessToken, sendData).execute().get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        return data;
    }

    // ------------------------------ GET Methods ------------------------------ //

    private String GET(String URL, String Authorization)
    {
        URL mUrl = null;
        HttpURLConnection mHttpURLConnection = null;
        InputStream mInputStream = null;
        String getData = "Error";

        try
        {
            mUrl = new URL(URL);
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setUseCaches(false);
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(false);
            mHttpURLConnection.setReadTimeout(12000);
            mHttpURLConnection.setConnectTimeout(25000);
            mHttpURLConnection.setChunkedStreamingMode(1000);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setRequestProperty("Accept", "application/json");
            mHttpURLConnection.setRequestProperty("Content-Type", "application/json");
            mHttpURLConnection.setRequestProperty("Authorization", Authorization);
            mHttpURLConnection.connect();

            int responseCode = mHttpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                mInputStream = mHttpURLConnection.getInputStream();

                if (mInputStream != null)
                {
                    getData = convertInputStreamToString(mInputStream);
                }
            }
            else
            {
                Log.e("Debug", "Erro ao tentar se conectar - " + String.valueOf(responseCode));
            }
        }
        catch (ProtocolException e)
        {
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (mHttpURLConnection != null)
            {
                mHttpURLConnection.disconnect();
            }
        }

        return getData;
    }

    private String GET(String URL, String Authorization, String XAccessToken)
    {
        URL mUrl = null;
        HttpURLConnection mHttpURLConnection = null;
        InputStream mInputStream = null;
        String getData = "Error";

        try
        {
            mUrl = new URL(URL);
            mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setUseCaches(false);
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(false);
            mHttpURLConnection.setReadTimeout(15000);
            mHttpURLConnection.setConnectTimeout(15000);
            mHttpURLConnection.setChunkedStreamingMode(1000);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setRequestProperty("Accept", "application/json");
            mHttpURLConnection.setRequestProperty("Content-Type", "application/json");
            mHttpURLConnection.setRequestProperty("Authorization", Authorization);
            mHttpURLConnection.setRequestProperty("X-Access-Token", XAccessToken);
            mHttpURLConnection.connect();

            int responseCode = mHttpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                mInputStream = mHttpURLConnection.getInputStream();

                if (mInputStream != null)
                {
                    getData = convertInputStreamToString(mInputStream);
                }
            }
            else
            {
                Log.e("Debug", "Erro ao tentar se conectar - " + String.valueOf(responseCode));
            }
        }
        catch (ProtocolException e)
        {
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (mHttpURLConnection != null)
            {
                mHttpURLConnection.disconnect();
            }
        }

        return getData;
    }

    private class AsyncTaskGET extends AsyncTask<String, Void, String>
    {
        String URL;
        String Authorization;
        String XAccessToken;

        private AsyncTaskGET()
        {
        }

        private AsyncTaskGET(String URL, String Authorization)
        {
            this.URL = URL;
            this.Authorization = Authorization;
        }

        private AsyncTaskGET(String URL, String Authorization, String XAccessToken)
        {
            this.URL = URL;
            this.Authorization = Authorization;
            this.XAccessToken = XAccessToken;
        }

        @Override
        protected String doInBackground(String... params)
        {
            if (XAccessToken == null)
            {
                return GET(URL, Authorization);
            }
            else
            {
                return GET(URL, Authorization, XAccessToken);
            }
        }

        @Override
        protected void onPostExecute(String s)
        {
            Log.d("Debug", s);
        }
    }

    public String requestGET(String URL, String Authorization)
    {
        String data = "Error";

        try
        {
            data = new AsyncTaskGET(URL, Authorization).execute().get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        return data;
    }

    public String requestGET(String URL, String Authorization, String XAccessToken)
    {
        String data = "Error";

        try
        {
            data = new AsyncTaskGET(URL, Authorization, XAccessToken).execute().get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        return data;
    }
}