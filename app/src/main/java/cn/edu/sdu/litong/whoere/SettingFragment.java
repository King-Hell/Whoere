package cn.edu.sdu.litong.whoere;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Button buttonLogin;
    private Button buttonLogout;
    private EditText editTextName;
    private EditText editTextPassword;

    private Socket socket = null;
    private ObjectOutputStream os = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String data;

    private String username;
    private String password;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        buttonLogin = (Button) view.findViewById(R.id.button_login);
        buttonLogout = (Button) view.findViewById(R.id.button_logout);
        editTextName = (EditText) view.findViewById(R.id.editText3);
        editTextPassword = (EditText) view.findViewById(R.id.editText5);
        buttonLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                out.println("BYE");
                MainActivity.isLogin=false;
                buttonLogout.setEnabled(false);
                buttonLogin.setEnabled(true);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.isLogin == true) {
                    Toast.makeText(getContext(), "请勿重复登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    username = editTextName.getText().toString();
                    password = editTextPassword.getText().toString();
                    socket = new Socket(InetAddress.getByName("kinghell.cn"), 49400);

                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    out.println("LOGIN");
                    while ((data = in.readLine()) != null) {
                        if (data.equals("LOGIN_YES")) {
                            os = new ObjectOutputStream(socket.getOutputStream());
                            MainActivity.account = new Account(username, password);
                            Account account = MainActivity.account;
                            os.writeObject(account);
                            os.flush();
                            break;
                        }
                    }
                    while ((data = in.readLine()) != null) {
                        if (data.equals("ACCOUNT_YES")) {
                            Toast.makeText(getContext(), "登录成功", Toast.LENGTH_LONG).show();
                            MainActivity.isLogin=true;
                        } else if (data.equals("ACCOUNT_NO")) {
                            Toast.makeText(getContext(), "密码错误或未找到该用户", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getContext(), "网络错误，登录失败", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(MainActivity.isLogin==true){
                    buttonLogout.setEnabled(true);
                    buttonLogin.setEnabled(false);
                }

            }
        });
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
}
