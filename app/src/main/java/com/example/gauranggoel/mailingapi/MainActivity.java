package com.example.gauranggoel.mailingapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/*
* in previous type -> our app is depandant on mailing apps
*
* now we will study -> mailing api
* can be sent mail -> without any mailing api
*
* you will 3 jar to send mail  (jar is in libs folder in d drive)
*
*
* what is api-> application programming interface
*
*  company always provide api of its product so that it can be used by developers
*
*  like ola uses Google Map
*
*  so far we have used google product as a user but now we will use it as developers
 *
 * for user they made application
 *
 *  for develpoers -> they dont use but rather -> they Integrate it in app
 *
 * like map is open in ola app instead map application
 *
 * we got some jar
 *
 * what is jar ? -> 2 types -> executable jar and normal jar
 * it is an archived file for collecting classes
 *
 * jar was used mostly to keep libraries like in java -> rt.jar (everything of java was kept in it)
 * for connecting to third party vendour -> they also provide jar in past
 *
 * jar was kept for security -> as it can be accessed only via person who has a access of our system
 * -> like can access without jar -> that may be on your system  but now you are outside -> has to install again
 *
 * now new concept is introduced -> libraries at server
 *
 * some company also keep -. in jar because they want to give to them who paid them only
 *
 * how to add jar to android
 *
 * Steps to add .jar file in android studio
 *
 * 1) copy your jar from your source
 * 2) open android project directory structure
 * 3) click on android tool and select project
 * 4) open project directory -> open app directory -> paste your jar file in libs direcctory  -> ok
 * 5) select all jar files -> right click -> select add as library tool -> ok
 * can chheck your jar in gradle
* */


/*
* class 2 ->
* UI has been made
* */
public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1= (EditText) findViewById(R.id.editText);
        et2= (EditText) findViewById(R.id.editText3);
        et3= (EditText) findViewById(R.id.editText2);
        btn= (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread t = new Thread(){
                    //so far studies 2ways to make thread -> lets thread 3 rd way
                    public void run(){
                        String subject = et1.getText().toString();
                        String message = et2.getText().toString();
                        String to=et3.getText().toString();
                        //process sechdule threads-> therefore we get assure that out task will be done for sure

                        String host="smtp.gmail.com";
                        //google mailing api
                        String from = "gauranggoel.9@gmail.com";
                        String pass="";

                        try{
                            Properties p = new Properties();


                            //store file in phone internlly to store cookies
                            Session session = Session.getInstance(p);
                            //get instance or cookie from properties -> cookie is part of browser here we called session

                            //session is class to manage service related to internet


                            //now need to use API

                            MimeMessage msg = new MimeMessage(session);
                            //this is responsible to send message

                            InternetAddress toId= new InternetAddress(to);
                            //message will be sent from sender-address to recipent-address
                            InternetAddress fromId= new InternetAddress(from);

                            msg.setRecipient(Message.RecipientType.TO,toId);
                            //use Message of package of javax.mail
                            //msg.setSender(fromId);
                            //will it work?
                            msg.setFrom(fromId);

                            //what data will be sent'

                            msg.setSubject(subject);
                            msg.setText(message);
                            //set data in mail

                            Transport tpt=session.getTransport("smtps");
                            //simple mail transfer protocol servic
                            //and get the refrence of transport class
                            //need transport service to send mail

                            tpt.connect(host,from,pass);
                            //host,user ,password

                            tpt.sendMessage(msg,msg.getAllRecipients());
                            //hold -> message and Recipients

                           // Toast.makeText(MainActivity.this, "mail gayiiiiiiiiiii", Toast.LENGTH_SHORT).show();

                            //need to get internet permission as well
                        }
                        catch(Exception e){

                        }

                    }
                };

                Toast.makeText(MainActivity.this, "mail gayi", Toast.LENGTH_SHORT).show();
                t.start();

                // first time get the mail from gmail -> click allow less secure app


            }
        });
    }

}

/*
*
* Assignment -> make registration form-> name phone email address register , send a mail to that mail -> you are logged in succesfully -> having username and password */