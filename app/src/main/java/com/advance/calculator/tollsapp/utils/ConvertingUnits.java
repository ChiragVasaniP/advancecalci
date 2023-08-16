package com.advance.calculator.tollsapp.utils;


public class ConvertingUnits {


    public static class Area {
        public double sqMilliToMeter(double milli) {
            return milli / 1000000.0d;
        }

        public double sqMeterToMilli(double meter) {
            return 1000000.0d * meter;
        }

        public double sqCentiToMeter(double Centi) {
            return Centi / 10000.0d;
        }

        public double sqMeterToCenti(double meter) {
            return 10000.0d * meter;
        }

        public double sqKiloToMeter(double Kilo) {
            return 1000000.0d * Kilo;
        }

        public double sqMeterToKilo(double meter) {
            return meter / 1000000.0d;
        }

        public double AcreToMeter(double acre) {
            return 4046.86d * acre;
        }

        public double sqMeterToAcre(double meter) {
            return meter / 4046.86d;
        }

        public double HectareToMeter(double Hectare) {
            return 10000.0d * Hectare;
        }

        public double sqMeterToHectare(double meter) {
            return meter / 10000.0d;
        }
    }


    public static class Length {
        public double MilliToMeter(double milli) {
            return milli / 1000.0d;
        }

        public double MeterToMilli(double meter) {
            return 1000.0d * meter;
        }

        public double CentiToMeter(double Centi) {
            return Centi / 100.0d;
        }

        public double MeterToCenti(double meter) {
            return 100.0d * meter;
        }

        public double KiloToMeter(double Kilo) {
            return 1000.0d * Kilo;
        }

        public double MeterToKilo(double meter) {
            return meter / 1000.0d;
        }

        public double InchToMeter(double Inch) {
            return Inch / 39.3701d;
        }

        public double MeterToInch(double meter) {
            return 39.3701d * meter;
        }

        public double FootToMeter(double Foot) {
            return Foot / 3.28084d;
        }

        public double MeterToFoot(double meter) {
            return 3.28084d * meter;
        }

        public double YardToMeter(double Yard) {
            return Yard / 1.09361d;
        }

        public double MeterToYard(double meter) {
            return 1.09361d * meter;
        }

        public double MileToMeter(double Mile) {
            return Mile / 6.21371E-4d;
        }

        public double MeterToMile(double meter) {
            return 6.21371E-4d * meter;
        }

        public double NanoToMeter(double milli) {
            return milli / 1.0E9d;
        }

        public double MeterToNano(double meter) {
            return 1.0E9d * meter;
        }
    }


    public static class Temperature {
        public double FerToKelvin(double fer) {
            return ((459.67d + fer) * 5.0d) / 9.0d;
        }

        public double KelvinToFer(double kelvin) {
            return ((9.0d * kelvin) / 5.0d) - 459.67d;
        }

        public double CelsiTokelvin(double Celsi) {
            return 273.15d + Celsi;
        }

        public double KelvinToCelsi(double Kelvin) {
            return Kelvin - 273.15d;
        }
    }


    public static class Weight {
        public double MilliToKilo(double milli) {
            return milli / 1000000.0d;
        }

        public double KiloToMilli(double Kilo) {
            return 1000000.0d * Kilo;
        }

        public double GramToKilo(double Gram) {
            return Gram / 1000.0d;
        }

        public double KiloToGram(double Kilo) {
            return 1000.0d * Kilo;
        }

        public double CentiToKilo(double Centi) {
            return Centi / 100000.0d;
        }

        public double KiloToCenti(double Kilo) {
            return 100000.0d * Kilo;
        }

        public double DeciToKilo(double Deci) {
            return Deci / 10000.0d;
        }

        public double KiloToDeci(double Kilo) {
            return 10000.0d * Kilo;
        }

        public double MetricTonnesToKilo(double MetricTonnes) {
            return 1000.0d * MetricTonnes;
        }

        public double KiloToMetricTonnes(double Kilo) {
            return Kilo / 1000.0d;
        }

        public double PoundsToKilo(double Pounds) {
            return Pounds / 2.20462d;
        }

        public double KiloToPounds(double Kilo) {
            return 2.20462d * Kilo;
        }

        public double OuncesToKilo(double Ounces) {
            return Ounces / 35.274d;
        }

        public double KiloToOunces(double Kilo) {
            return 35.274d * Kilo;
        }
    }
}
