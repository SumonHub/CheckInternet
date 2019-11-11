# CheckInternet
an easy-to-use tool for look up internet connection in realtime.
# Gradle Dependency
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

      allprojects {
          repositories {
            ...
            maven { url 'https://jitpack.io' }
          }
        }
Step 2. Add the dependency
      
      dependencies {
                implementation 'com.github.SumonHub:CheckInternet:1.0.0'
        }
        
# To-do
Step 1. In manifest file, add permission
      
      <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
      
Step 2. In manifest file, register service

       <application
             .
             .
             .
             .
            <service android:name="com.sumon.checkinternettool.CheckConnectivity" />
        </application>
              
Step 3. Start Service in your onCreate().
 
      protected void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.activity_main);

              startService(new Intent(MainActivity.this, CheckConnectivity.class));

             '
             '
             '
          }

Step 4. Filally get connection status

      protected void onCreate(Bundle savedInstanceState) {
              .
              .
              .
              CheckConnectivity.connectionStatus.observe(this, new Observer<Boolean>() {
                  @Override
                  public void onChanged(Boolean aBoolean) {
                      Log.d(TAG, "connectionStatus onChanged: " + aBoolean);
                      if (!aBoolean) {
                          Utils.showDialog(MainActivity.this);
                      } else {
                          Utils.stopDialog();
                      }
                  }
              });
          }
          
Step 5. Do not forget to Stop Service in your onDestroy().
 
      @Override
          protected void onDestroy() {
              super.onDestroy();
              stopService(new Intent(this, CheckConnectivity.class));
          }
