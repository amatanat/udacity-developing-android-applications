package com.example.android.boardingpass;

/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.android.boardingpass.databinding.ActivityMainBinding;
import com.example.android.boardingpass.utilities.FakeDataUtils;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // (3) Create a data binding instance called mBinding of type ActivityMainBinding
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //  (4) Set the Content View using DataBindingUtil to the activity_main layout
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //  (5) Load a BoardingPassInfo object with fake data using FakeDataUtils

        BoardingPassInfo fakeDataUtils = FakeDataUtils.generateFakeBoardingPassInfo();

        //  (9) Call displayBoardingPassInfo and pass the fake BoardingInfo instance
        displayBoardingPassInfo(fakeDataUtils);

    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        //  (6) Use mBinding to set the Text in all the textViews using the data in info
        mBinding.textViewPassengerName.setText(info.passengerName);
        mBinding.textViewDestinationAirport.setText(info.destCode);
        mBinding.textViewOriginAirport.setText(info.originCode);
        mBinding.textViewFlightCode.setText(info.flightCode);
        mBinding.textViewGate.setText(info.departureGate);
        mBinding.textViewSeat.setText(info.seatNumber);
        mBinding.textViewTerminal.setText(info.departureTerminal);

        //  (7) Use a SimpleDateFormat formatter to set the formatted value in time text views
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:a", Locale.getDefault());
        String boardingTime = formatter.format(info.boardingTime);
        String departureTime = formatter.format(info.departureTime);
        String arrivalTime = formatter.format(info.arrivalTime);

        mBinding.textViewBoardingTime.setText(boardingTime);
        mBinding.textViewDepartureTime.setText(departureTime);
        mBinding.textViewArrivalTime.setText(arrivalTime);


        //  (8) Use TimeUnit methods to format the total minutes until boarding
        long totalMInutesUntilBoarding = info.getMinutesUntilBoarding();
        long totalHourUntilBoarding = TimeUnit.MINUTES.toHours(totalMInutesUntilBoarding);
        long minutesLessHours = totalMInutesUntilBoarding - TimeUnit.HOURS.toMinutes(totalHourUntilBoarding);

        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat, totalHourUntilBoarding, minutesLessHours);
        mBinding.textViewBoardingInCountdown.setText(hoursAndMinutesUntilBoarding);

    }
}

