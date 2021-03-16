package com.bwap.weatherapp.WeatherApp.view;

import com.bwap.weatherapp.WeatherApp.Model.Main;
import com.bwap.weatherapp.WeatherApp.controller.WeatherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@SpringUI(path="")
public class MainView extends UI {

    private VerticalLayout mainLayout;
    private NativeSelect<String> unitSelect;
    private TextField cityTextField;
    private Button searchButton;
    private HorizontalLayout Dashbaord;
    private Label location;
    private Label currentTemp;
    private HorizontalLayout mainDescriptionLayout;
    private Label weatherDesc;
    private Label maxWeather,minWeather,humidity,pressure,wind,feelsLike;
    private Image iconImage;


    @Autowired
    private WeatherService weatherService;




    @Override
    protected void init(VaadinRequest vaadinRequest) {
          mainLayout();
          setHeader();
          setLogo();
          setForm();
        dashboardTitle();
        dashboardDetails();
        searchButton.addClickListener(clickEvent -> {
            if(!cityTextField.getValue().equals("")){
                try {
                    updateUI();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else{
                Notification.show("Enter the city");
            }
        });
    }

    private void updateUI() throws JSONException {
        String city=cityTextField.getValue();
        String defaultUnit;

        weatherService.setCityName(city);
        if(unitSelect.getValue().equals("F")){
            weatherService.setUnit("imperials");
            unitSelect.setValue("F");
            defaultUnit="\u00b0" + "F";
        }else{
            weatherService.setUnit("metric");
            unitSelect.setValue("C");
            defaultUnit="\u00b0" + "C";
        }


        location.setValue("Currently in" + " " + city);

        weatherService.build();


        JSONObject mainObject=weatherService.returnMain();


        int temp=mainObject.getInt("temp");
        currentTemp.setValue(temp+defaultUnit);

        //getting icon from API
        String iconCode=null;
        String weatherDescNew=null;

        JSONArray jsonArray=weatherService.returnWeatherArray();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject weatherObj=jsonArray.getJSONObject(i);
            iconCode=weatherObj.getString("icon");
            weatherDescNew=weatherObj.getString("description");
        }
        iconImage.setSource(new ExternalResource("http://openweathermap.org/img/wn/"+iconCode+"@2x.png"));
        weatherDesc.setValue("Description" + " "+weatherDescNew);
        minWeather.setValue("MinTemp:"+weatherService.returnMain().getDouble("temp_min")+unitSelect.getValue());
        maxWeather.setValue("MaxTemp:"+weatherService.returnMain().getDouble("temp_max")+unitSelect.getValue());
        pressure.setValue("Pressure:"+weatherService.returnMain().getDouble("pressure"));
        humidity.setValue("Humidity:"+weatherService.returnMain().getDouble("humidity"));
        wind.setValue("Wind:"+weatherService.returnWind().getDouble("speed"));
        feelsLike.setValue("Feels like:"+weatherService.returnMain().getDouble("feels_like"));

        mainLayout.addComponents(Dashbaord,mainDescriptionLayout);

    }

    private void setForm() {
        HorizontalLayout formLayout=new HorizontalLayout();
        formLayout.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        unitSelect=new NativeSelect<>();
        ArrayList<String> items=new ArrayList<>();
        items.add("C");
        items.add("F");
        unitSelect.setItems(items);
        unitSelect.setValue(items.get(0));
        formLayout.addComponent(unitSelect);

        //cityTextField
        cityTextField=new TextField();
        cityTextField.setWidth("80%");
        formLayout.addComponent(cityTextField);


        //searchBUtton
        searchButton=new Button();
        searchButton.setIcon(VaadinIcons.SEARCH);
        formLayout.addComponent(searchButton);



        mainLayout.addComponent(formLayout);
    }

    private void setLogo() {
        HorizontalLayout logo=new HorizontalLayout();
        logo.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Image i=new Image("Logo",new ClassResource("/static/logo.png"));
        logo.setHeight("280px");
        logo.setWidth("280px");
        logo.addComponent(i);
        mainLayout.addComponent(logo);
    }

    private void mainLayout() {
        iconImage=new Image();
        mainLayout=new VerticalLayout();
        mainLayout.setWidth("100%");
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        setContent(mainLayout);
    }

    private void setHeader(){
        HorizontalLayout header=new HorizontalLayout();
        header.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label label=new Label("Weather app by sudhanshu");
        header.addComponent(label);
        mainLayout.addComponent(header);
    }

    private void dashboardTitle(){
        Dashbaord=new HorizontalLayout();
        Dashbaord.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        //city location
        location=new Label("currently in Allahabad");
        location.addStyleName(ValoTheme.LABEL_H2);
        location.addStyleName(ValoTheme.LABEL_LIGHT);

        //curretn temp
        currentTemp=new Label("10C");
        currentTemp.addStyleName(ValoTheme.LABEL_BOLD);
        currentTemp.addStyleName(ValoTheme.LABEL_LIGHT);

        Dashbaord.addComponents(location,iconImage,currentTemp);


    }

    private void dashboardDetails(){
        mainDescriptionLayout=new HorizontalLayout();
        mainDescriptionLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        //description layout
        VerticalLayout descriptionLayout=new VerticalLayout();
        descriptionLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        //weather description
        weatherDesc=new Label("Description: Clear Skies");
        weatherDesc.setStyleName(ValoTheme.LABEL_SUCCESS);
        descriptionLayout.addComponent(weatherDesc);

        //min weather;
        minWeather=new Label("Min:53");
        descriptionLayout.addComponent(minWeather);

        //Max weather
        maxWeather=new Label("Max:53");
        descriptionLayout.addComponent(maxWeather);

        VerticalLayout pressureLayout=new VerticalLayout();
        pressureLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        //pressure
        pressure=new Label("Pressure");
        pressureLayout.addComponent(pressure);

        //Humidity
        humidity=new Label("Humidity");
        pressureLayout.addComponent(humidity);

        //wind
        wind=new Label("Wind");
        pressureLayout.addComponent(wind);

        //feels like
        feelsLike=new Label("feels Like");
        pressureLayout.addComponent(feelsLike);
        mainDescriptionLayout.addComponents(pressureLayout,descriptionLayout);

    }
}
