

public class login extends AppCompatActivity
{
    public String Output_From_PHP;//For unit Testing purpose
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            //User Login On Click Listener
            final EditText user = (EditText) findViewById(R.id./*Insert Id*/);
            final EditText pass = (EditText) findViewById(R.id./*Insert Id*/);

            final Button login_btn = (Button) findViewById(R.id./*Insert Id*/);
            login_btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {

                    final String username = user.getText().toString();
                    final String password = pass.getText().toString();
                    ContentValues params = new ContentValues();
                    params.put("userName", username);
                    params.put("password", password);

                    @SuppressLint("StaticFieldLeak") AsyncHttpPost asyncHTTPPost = new AsyncHttpPost("http://lamp.ms.wits.ac.za/~s1965919/login.php", params)
                    {
                        @Override
                        protected void onPostExecute(String output)
                        {
                            Cusor results = new Cusor();
                            JSONArray output_array = new JSONArray();
                            Output_From_PHP = output;//for unit Testing purpose
                            if (output.contains(/*output from php that indicates that user does not exist*/))
                            {
                                Toast.makeText(login.this, "username does not exist", Toast.LENGTH_SHORT).show();
                            }
                            else if (output.contains(password))//if password and username exist and match
                            {
                                Intent main =new Intent(login.this,/*new Marketplace class*/);
                                main.putExtra("username", username); //stores for later display use of student number
                                startActivity(main);
                            }
                            else//if username exist but wrong password and other errors that might occur
                            {
                                Toast.makeText(login.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    asyncHTTPPost.execute();
                }
            });

            //User Register On Click Listener
            TextView register_prompt = (TextView) findViewById(R.id./*Insert Id*/);
            register_prompt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent register = new Intent(login.this, /*Register class*/);
                    startActivity(register);
                }
            });



        }
    }
