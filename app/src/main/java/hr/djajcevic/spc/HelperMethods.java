package hr.djajcevic.spc;

import static java.lang.Math.*;

/**
 * Created by djajcevic on 18.04.15..
 */
public class HelperMethods {

    public static double S_PI = 3.1415926535897932384626433832795028841971;
    public static double SUN_RADIUS = 0.26667;

    public static int L_COUNT = 6;
    public static int B_COUNT = 2;
    public static int R_COUNT = 5;
    public static int Y_COUNT = 63;

    public static int L_MAX_SUBCOUNT = 64;
    public static int B_MAX_SUBCOUNT = 5;
    public static int R_MAX_SUBCOUNT = 40;

    public static int TERM_X0 = 0;
    public static int TERM_X1 = 1;
    public static int TERM_X2 = 2;
    public static int TERM_X3 = 3;
    public static int TERM_X4 = 4;
    public static int TERM_X_COUNT = 5;

    public static int TERM_PSI_A = 0;
    public static int TERM_PSI_B = 1;
    public static int TERM_EPS_C = 2;
    public static int TERM_EPS_D = 3;
    public static int TERM_PE_COUNT = 4;

    public static int TERM_A = 0;
    public static int TERM_B = 1;
    public static int TERM_C = 2;

    public static int JD_MINUS = 0;
    public static int JD_ZERO = 1;
    public static int JD_PLUS = 2;
    public static int JD_COUNT = 3;

    public static int TERM_D = 0;
    public static int TERM_M = 1;
    public static int TERM_MPR = 2;
    public static int TERM_F = 3;
    public static int TERM_LB = 4;
    public static int TERM_R = 5;
    public static int TERM_COUNT = 6;

    public static int SUN_TRANSIT = 0;
    public static int SUN_RISE = 1;
    public static int SUN_SET = 2;
    public static int SUN_COUNT = 3;

    static int TERM_Y_COUNT = TERM_X_COUNT;

    // [L_COUNT]
    static int[] l_subcount = {64, 34, 20, 7, 3, 1};
    // [B_COUNT]
    static int[] b_subcount = {5, 2};
    // [R_COUNT]
    static int[] r_subcount = {40, 10, 6, 2, 1};

    public static int COUNT = 60;

    ///////////////////////////////////////////////////
///  Earth Periodic Terms
///////////////////////////////////////////////////
    // [L_COUNT][L_MAX_SUBCOUNT][TERM_COUNT]
    static double L_TERMS[][][] =
            {
                    {
                            {175347046.0, 0, 0},
                            {3341656.0, 4.6692568, 6283.07585},
                            {34894.0, 4.6261, 12566.1517},
                            {3497.0, 2.7441, 5753.3849},
                            {3418.0, 2.8289, 3.5231},
                            {3136.0, 3.6277, 77713.7715},
                            {2676.0, 4.4181, 7860.4194},
                            {2343.0, 6.1352, 3930.2097},
                            {1324.0, 0.7425, 11506.7698},
                            {1273.0, 2.0371, 529.691},
                            {1199.0, 1.1096, 1577.3435},
                            {990, 5.233, 5884.927},
                            {902, 2.045, 26.298},
                            {857, 3.508, 398.149},
                            {780, 1.179, 5223.694},
                            {753, 2.533, 5507.553},
                            {505, 4.583, 18849.228},
                            {492, 4.205, 775.523},
                            {357, 2.92, 0.067},
                            {317, 5.849, 11790.629},
                            {284, 1.899, 796.298},
                            {271, 0.315, 10977.079},
                            {243, 0.345, 5486.778},
                            {206, 4.806, 2544.314},
                            {205, 1.869, 5573.143},
                            {202, 2.458, 6069.777},
                            {156, 0.833, 213.299},
                            {132, 3.411, 2942.463},
                            {126, 1.083, 20.775},
                            {115, 0.645, 0.98},
                            {103, 0.636, 4694.003},
                            {102, 0.976, 15720.839},
                            {102, 4.267, 7.114},
                            {99, 6.21, 2146.17},
                            {98, 0.68, 155.42},
                            {86, 5.98, 161000.69},
                            {85, 1.3, 6275.96},
                            {85, 3.67, 71430.7},
                            {80, 1.81, 17260.15},
                            {79, 3.04, 12036.46},
                            {75, 1.76, 5088.63},
                            {74, 3.5, 3154.69},
                            {74, 4.68, 801.82},
                            {70, 0.83, 9437.76},
                            {62, 3.98, 8827.39},
                            {61, 1.82, 7084.9},
                            {57, 2.78, 6286.6},
                            {56, 4.39, 14143.5},
                            {56, 3.47, 6279.55},
                            {52, 0.19, 12139.55},
                            {52, 1.33, 1748.02},
                            {51, 0.28, 5856.48},
                            {49, 0.49, 1194.45},
                            {41, 5.37, 8429.24},
                            {41, 2.4, 19651.05},
                            {39, 6.17, 10447.39},
                            {37, 6.04, 10213.29},
                            {37, 2.57, 1059.38},
                            {36, 1.71, 2352.87},
                            {36, 1.78, 6812.77},
                            {33, 0.59, 17789.85},
                            {30, 0.44, 83996.85},
                            {30, 2.74, 1349.87},
                            {25, 3.16, 4690.48}
                    },
                    {
                            {628331966747.0, 0, 0},
                            {206059.0, 2.678235, 6283.07585},
                            {4303.0, 2.6351, 12566.1517},
                            {425.0, 1.59, 3.523},
                            {119.0, 5.796, 26.298},
                            {109.0, 2.966, 1577.344},
                            {93, 2.59, 18849.23},
                            {72, 1.14, 529.69},
                            {68, 1.87, 398.15},
                            {67, 4.41, 5507.55},
                            {59, 2.89, 5223.69},
                            {56, 2.17, 155.42},
                            {45, 0.4, 796.3},
                            {36, 0.47, 775.52},
                            {29, 2.65, 7.11},
                            {21, 5.34, 0.98},
                            {19, 1.85, 5486.78},
                            {19, 4.97, 213.3},
                            {17, 2.99, 6275.96},
                            {16, 0.03, 2544.31},
                            {16, 1.43, 2146.17},
                            {15, 1.21, 10977.08},
                            {12, 2.83, 1748.02},
                            {12, 3.26, 5088.63},
                            {12, 5.27, 1194.45},
                            {12, 2.08, 4694},
                            {11, 0.77, 553.57},
                            {10, 1.3, 6286.6},
                            {10, 4.24, 1349.87},
                            {9, 2.7, 242.73},
                            {9, 5.64, 951.72},
                            {8, 5.3, 2352.87},
                            {6, 2.65, 9437.76},
                            {6, 4.67, 4690.48}
                    },
                    {
                            {52919.0, 0, 0},
                            {8720.0, 1.0721, 6283.0758},
                            {309.0, 0.867, 12566.152},
                            {27, 0.05, 3.52},
                            {16, 5.19, 26.3},
                            {16, 3.68, 155.42},
                            {10, 0.76, 18849.23},
                            {9, 2.06, 77713.77},
                            {7, 0.83, 775.52},
                            {5, 4.66, 1577.34},
                            {4, 1.03, 7.11},
                            {4, 3.44, 5573.14},
                            {3, 5.14, 796.3},
                            {3, 6.05, 5507.55},
                            {3, 1.19, 242.73},
                            {3, 6.12, 529.69},
                            {3, 0.31, 398.15},
                            {3, 2.28, 553.57},
                            {2, 4.38, 5223.69},
                            {2, 3.75, 0.98}
                    },
                    {
                            {289.0, 5.844, 6283.076},
                            {35, 0, 0},
                            {17, 5.49, 12566.15},
                            {3, 5.2, 155.42},
                            {1, 4.72, 3.52},
                            {1, 5.3, 18849.23},
                            {1, 5.97, 242.73}
                    },
                    {
                            {114.0, 3.142, 0},
                            {8, 4.13, 6283.08},
                            {1, 3.84, 12566.15}
                    },
                    {
                            {1, 3.14, 0}
                    }
            };

    //[B_COUNT][B_MAX_SUBCOUNT][TERM_COUNT]
    static double[][][] B_TERMS =
            {
                    {
                            {280.0, 3.199, 84334.662},
                            {102.0, 5.422, 5507.553},
                            {80, 3.88, 5223.69},
                            {44, 3.7, 2352.87},
                            {32, 4, 1577.34}
                    },
                    {
                            {9, 3.9, 5507.55},
                            {6, 1.73, 5223.69}
                    }
            };

    //[R_COUNT][R_MAX_SUBCOUNT][TERM_COUNT]
    static double[][][] R_TERMS =
            {
                    {
                            {100013989.0, 0, 0},
                            {1670700.0, 3.0984635, 6283.07585},
                            {13956.0, 3.05525, 12566.1517},
                            {3084.0, 5.1985, 77713.7715},
                            {1628.0, 1.1739, 5753.3849},
                            {1576.0, 2.8469, 7860.4194},
                            {925.0, 5.453, 11506.77},
                            {542.0, 4.564, 3930.21},
                            {472.0, 3.661, 5884.927},
                            {346.0, 0.964, 5507.553},
                            {329.0, 5.9, 5223.694},
                            {307.0, 0.299, 5573.143},
                            {243.0, 4.273, 11790.629},
                            {212.0, 5.847, 1577.344},
                            {186.0, 5.022, 10977.079},
                            {175.0, 3.012, 18849.228},
                            {110.0, 5.055, 5486.778},
                            {98, 0.89, 6069.78},
                            {86, 5.69, 15720.84},
                            {86, 1.27, 161000.69},
                            {65, 0.27, 17260.15},
                            {63, 0.92, 529.69},
                            {57, 2.01, 83996.85},
                            {56, 5.24, 71430.7},
                            {49, 3.25, 2544.31},
                            {47, 2.58, 775.52},
                            {45, 5.54, 9437.76},
                            {43, 6.01, 6275.96},
                            {39, 5.36, 4694},
                            {38, 2.39, 8827.39},
                            {37, 0.83, 19651.05},
                            {37, 4.9, 12139.55},
                            {36, 1.67, 12036.46},
                            {35, 1.84, 2942.46},
                            {33, 0.24, 7084.9},
                            {32, 0.18, 5088.63},
                            {32, 1.78, 398.15},
                            {28, 1.21, 6286.6},
                            {28, 1.9, 6279.55},
                            {26, 4.59, 10447.39}
                    },
                    {
                            {103019.0, 1.10749, 6283.07585},
                            {1721.0, 1.0644, 12566.1517},
                            {702.0, 3.142, 0},
                            {32, 1.02, 18849.23},
                            {31, 2.84, 5507.55},
                            {25, 1.32, 5223.69},
                            {18, 1.42, 1577.34},
                            {10, 5.91, 10977.08},
                            {9, 1.42, 6275.96},
                            {9, 0.27, 5486.78}
                    },
                    {
                            {4359.0, 5.7846, 6283.0758},
                            {124.0, 5.579, 12566.152},
                            {12, 3.14, 0},
                            {9, 3.63, 77713.77},
                            {6, 1.87, 5573.14},
                            {3, 5.47, 18849.23}
                    },
                    {
                            {145.0, 4.273, 6283.076},
                            {7, 3.92, 12566.15}
                    },
                    {
                            {4, 2.56, 6283.08}
                    }
            };

    ////////////////////////////////////////////////////////////////
///  Periodic Terms for the nutation in longitude and obliquity
////////////////////////////////////////////////////////////////

    // [Y_COUNT][TERM_Y_COUNT]
    static int[][] Y_TERMS =
            {
                    {0, 0, 0, 0, 1},
                    {-2, 0, 0, 2, 2},
                    {0, 0, 0, 2, 2},
                    {0, 0, 0, 0, 2},
                    {0, 1, 0, 0, 0},
                    {0, 0, 1, 0, 0},
                    {-2, 1, 0, 2, 2},
                    {0, 0, 0, 2, 1},
                    {0, 0, 1, 2, 2},
                    {-2, -1, 0, 2, 2},
                    {-2, 0, 1, 0, 0},
                    {-2, 0, 0, 2, 1},
                    {0, 0, -1, 2, 2},
                    {2, 0, 0, 0, 0},
                    {0, 0, 1, 0, 1},
                    {2, 0, -1, 2, 2},
                    {0, 0, -1, 0, 1},
                    {0, 0, 1, 2, 1},
                    {-2, 0, 2, 0, 0},
                    {0, 0, -2, 2, 1},
                    {2, 0, 0, 2, 2},
                    {0, 0, 2, 2, 2},
                    {0, 0, 2, 0, 0},
                    {-2, 0, 1, 2, 2},
                    {0, 0, 0, 2, 0},
                    {-2, 0, 0, 2, 0},
                    {0, 0, -1, 2, 1},
                    {0, 2, 0, 0, 0},
                    {2, 0, -1, 0, 1},
                    {-2, 2, 0, 2, 2},
                    {0, 1, 0, 0, 1},
                    {-2, 0, 1, 0, 1},
                    {0, -1, 0, 0, 1},
                    {0, 0, 2, -2, 0},
                    {2, 0, -1, 2, 1},
                    {2, 0, 1, 2, 2},
                    {0, 1, 0, 2, 2},
                    {-2, 1, 1, 0, 0},
                    {0, -1, 0, 2, 2},
                    {2, 0, 0, 2, 1},
                    {2, 0, 1, 0, 0},
                    {-2, 0, 2, 2, 2},
                    {-2, 0, 1, 2, 1},
                    {2, 0, -2, 0, 1},
                    {2, 0, 0, 0, 1},
                    {0, -1, 1, 0, 0},
                    {-2, -1, 0, 2, 1},
                    {-2, 0, 0, 0, 1},
                    {0, 0, 2, 2, 1},
                    {-2, 0, 2, 0, 1},
                    {-2, 1, 0, 2, 1},
                    {0, 0, 1, -2, 0},
                    {-1, 0, 1, 0, 0},
                    {-2, 1, 0, 0, 0},
                    {1, 0, 0, 0, 0},
                    {0, 0, 1, 2, 0},
                    {0, 0, -2, 2, 2},
                    {-1, -1, 1, 0, 0},
                    {0, 1, 1, 0, 0},
                    {0, -1, 1, 2, 2},
                    {2, -1, -1, 2, 2},
                    {0, 0, 3, 2, 2},
                    {2, -1, 0, 2, 2},
            };

    // [Y_COUNT][TERM_PE_COUNT]
    static double[][] PE_TERMS = {
            {-171996, -174.2, 92025, 8.9},
            {-13187, -1.6, 5736, -3.1},
            {-2274, -0.2, 977, -0.5},
            {2062, 0.2, -895, 0.5},
            {1426, -3.4, 54, -0.1},
            {712, 0.1, -7, 0},
            {-517, 1.2, 224, -0.6},
            {-386, -0.4, 200, 0},
            {-301, 0, 129, -0.1},
            {217, -0.5, -95, 0.3},
            {-158, 0, 0, 0},
            {129, 0.1, -70, 0},
            {123, 0, -53, 0},
            {63, 0, 0, 0},
            {63, 0.1, -33, 0},
            {-59, 0, 26, 0},
            {-58, -0.1, 32, 0},
            {-51, 0, 27, 0},
            {48, 0, 0, 0},
            {46, 0, -24, 0},
            {-38, 0, 16, 0},
            {-31, 0, 13, 0},
            {29, 0, 0, 0},
            {29, 0, -12, 0},
            {26, 0, 0, 0},
            {-22, 0, 0, 0},
            {21, 0, -10, 0},
            {17, -0.1, 0, 0},
            {16, 0, -8, 0},
            {-16, 0.1, 7, 0},
            {-15, 0, 9, 0},
            {-13, 0, 7, 0},
            {-12, 0, 6, 0},
            {11, 0, 0, 0},
            {-10, 0, 5, 0},
            {-8, 0, 3, 0},
            {7, 0, -3, 0},
            {-7, 0, 0, 0},
            {-7, 0, 3, 0},
            {-7, 0, 3, 0},
            {6, 0, 0, 0},
            {6, 0, -3, 0},
            {6, 0, -3, 0},
            {-6, 0, 3, 0},
            {-6, 0, 3, 0},
            {5, 0, 0, 0},
            {-5, 0, 3, 0},
            {-5, 0, 3, 0},
            {-5, 0, 3, 0},
            {4, 0, 0, 0},
            {4, 0, 0, 0},
            {4, 0, 0, 0},
            {-4, 0, 0, 0},
            {-4, 0, 0, 0},
            {-4, 0, 0, 0},
            {3, 0, 0, 0},
            {-3, 0, 0, 0},
            {-3, 0, 0, 0},
            {-3, 0, 0, 0},
            {-3, 0, 0, 0},
            {-3, 0, 0, 0},
            {-3, 0, 0, 0},
            {-3, 0, 0, 0},
    };

    static double rad2deg(double radians) {
        return (180.0 / S_PI) * radians;
    }

    static double deg2rad(double degrees) {
        return (S_PI / 180.0) * degrees;
    }

    static int integer(double value) {
        return (int) value;
    }

    static double limit_degrees(double degrees) {
        double limited;

        degrees /= 360.0;
        limited = 360.0 * (degrees - floor(degrees));
        if (limited < 0) limited += 360.0;

        return limited;
    }

    static double limit_degrees180pm(double degrees) {
        double limited;

        degrees /= 360.0;
        limited = 360.0 * (degrees - floor(degrees));
        if (limited < -180.0) limited += 360.0;
        else if (limited > 180.0) limited -= 360.0;

        return limited;
    }

    static double limit_degrees180(double degrees) {
        double limited;

        degrees /= 180.0;
        limited = 180.0 * (degrees - floor(degrees));
        if (limited < 0) limited += 180.0;

        return limited;
    }

    static double limit_zero2one(double value) {
        double limited;

        limited = value - floor(value);
        if (limited < 0) limited += 1.0;

        return limited;
    }

    static double limit_minutes(double minutes) {
        double limited = minutes;

        if (limited < -20.0) limited += 1440.0;
        else if (limited > 20.0) limited -= 1440.0;

        return limited;
    }

    static double dayfrac_to_local_hr(double dayfrac, double timezone) {
        return 24.0 * limit_zero2one(dayfrac + timezone / 24.0);
    }

    static double third_order_polynomial(double a, double b, double c, double d, double x) {
        return ((a * x + b) * x + c) * x + d;
    }

    static double julian_day(int year, int month, int day, int hour, int minute, double second, double dut1, double tz) {
        double day_decimal, julian_day, a;

        day_decimal = day + (hour - tz + (minute + (second + dut1) / 60.0) / 60.0) / 24.0;

        if (month < 3) {
            month += 12;
            year--;
        }

        julian_day = integer(365.25 * (year + 4716.0)) + integer(30.6001 * (month + 1)) + day_decimal - 1524.5;

        if (julian_day > 2299160.0) {
            a = integer(year / 100);
            julian_day += (2 - a + integer(a / 4));
        }

        return julian_day;
    }

    static double julian_century(double jd) {
        return (jd - 2451545.0) / 36525.0;
    }

    static double julian_ephemeris_day(double jd, double delta_t) {
        return jd + delta_t / 86400.0;
    }

    static double julian_ephemeris_century(double jde) {
        return (jde - 2451545.0) / 36525.0;
    }

    static double julian_ephemeris_millennium(double jce) {
        return (jce / 10.0);
    }

    static double earth_periodic_term_summation(double[][] terms, int count, double jme) {
        int i;
        double sum = 0;

        for (i = 0; i < count; i++)
            sum += terms[i][TERM_A] * cos(terms[i][TERM_B] + terms[i][TERM_C] * jme);

        return sum;
    }

    static double earth_values(double term_sum[], int count, double jme) {
        int i;
        double sum = 0;

        for (i = 0; i < count; i++)
            sum += term_sum[i] * pow(jme, i);

        sum /= 1.0e8;

        return sum;
    }

    static double earth_heliocentric_longitude(double jme) {
        double[] sum = new double[L_COUNT];
        int i;

        for (i = 0; i < L_COUNT; i++)
            sum[i] = earth_periodic_term_summation(L_TERMS[i], l_subcount[i], jme);

        return limit_degrees(rad2deg(earth_values(sum, L_COUNT, jme)));

    }

    static double earth_heliocentric_latitude(double jme) {
        double[] sum = new double[B_COUNT];
        int i;

        for (i = 0; i < B_COUNT; i++)
            sum[i] = earth_periodic_term_summation(B_TERMS[i], b_subcount[i], jme);

        return rad2deg(earth_values(sum, B_COUNT, jme));

    }

    static double earth_radius_vector(double jme) {
        double[] sum = new double[R_COUNT];
        int i;

        for (i = 0; i < R_COUNT; i++)
            sum[i] = earth_periodic_term_summation(R_TERMS[i], r_subcount[i], jme);

        return earth_values(sum, R_COUNT, jme);

    }

    static double geocentric_longitude(double l) {
        double theta = l + 180.0;

        if (theta >= 360.0) theta -= 360.0;

        return theta;
    }

    static double geocentric_latitude(double b) {
        return -b;
    }

    static double mean_elongation_moon_sun(double jce) {
        return third_order_polynomial(1.0 / 189474.0, -0.0019142, 445267.11148, 297.85036, jce);
    }

    static double mean_anomaly_sun(double jce) {
        return third_order_polynomial(-1.0 / 300000.0, -0.0001603, 35999.05034, 357.52772, jce);
    }

    static double mean_anomaly_moon(double jce) {
        return third_order_polynomial(1.0 / 56250.0, 0.0086972, 477198.867398, 134.96298, jce);
    }

    static double argument_latitude_moon(double jce) {
        return third_order_polynomial(1.0 / 327270.0, -0.0036825, 483202.017538, 93.27191, jce);
    }

    static double ascending_longitude_moon(double jce) {
        return third_order_polynomial(1.0 / 450000.0, 0.0020708, -1934.136261, 125.04452, jce);
    }

    static double xy_term_summation(int i, double[] x) {
        int j;
        double sum = 0;

        for (j = 0; j < x.length; j++)
            sum += x[j] * Y_TERMS[i][j];

        return sum;
    }

    static void nutation_longitude_and_obliquity(double jce, double[] x, double del_psi,
                                                 double del_epsilon) {
        int i;
        double xy_term_sum, sum_psi = 0, sum_epsilon = 0;

        for (i = 0; i < Y_COUNT; i++) {
            xy_term_sum = deg2rad(xy_term_summation(i, x));
            sum_psi += (PE_TERMS[i][TERM_PSI_A] + jce * PE_TERMS[i][TERM_PSI_B]) * sin(xy_term_sum);
            sum_epsilon += (PE_TERMS[i][TERM_EPS_C] + jce * PE_TERMS[i][TERM_EPS_D]) * cos(xy_term_sum);
        }

        del_psi = sum_psi / 36000000.0;
        del_epsilon = sum_epsilon / 36000000.0;
    }

    static double ecliptic_mean_obliquity(double jme) {
        double u = jme / 10.0;

        return 84381.448 + u * (-4680.93 + u * (-1.55 + u * (1999.25 + u * (-51.38 + u * (-249.67 +
                u * (-39.05 + u * (7.12 + u * (27.87 + u * (5.79 + u * 2.45)))))))));
    }

    static double ecliptic_true_obliquity(double delta_epsilon, double epsilon0) {
        return delta_epsilon + epsilon0 / 3600.0;
    }

    static double aberration_correction(double r) {
        return -20.4898 / (3600.0 * r);
    }

    static double apparent_sun_longitude(double theta, double delta_psi, double delta_tau) {
        return theta + delta_psi + delta_tau;
    }

    static double greenwich_mean_sidereal_time(double jd, double jc) {
        return limit_degrees(280.46061837 + 360.98564736629 * (jd - 2451545.0) +
                jc * jc * (0.000387933 - jc / 38710000.0));
    }

    static double greenwich_sidereal_time(double nu0, double delta_psi, double epsilon) {
        return nu0 + delta_psi * cos(deg2rad(epsilon));
    }

    static double geocentric_right_ascension(double lamda, double epsilon, double beta) {
        double lamda_rad = deg2rad(lamda);
        double epsilon_rad = deg2rad(epsilon);

        return limit_degrees(rad2deg(atan2(sin(lamda_rad) * cos(epsilon_rad) -
                tan(deg2rad(beta)) * sin(epsilon_rad), cos(lamda_rad))));
    }

    static double geocentric_declination(double beta, double epsilon, double lamda) {
        double beta_rad = deg2rad(beta);
        double epsilon_rad = deg2rad(epsilon);

        return rad2deg(asin(sin(beta_rad) * cos(epsilon_rad) +
                cos(beta_rad) * sin(epsilon_rad) * sin(deg2rad(lamda))));
    }

    static double observer_hour_angle(double nu, double longitude, double alpha_deg) {
        return limit_degrees(nu + longitude - alpha_deg);
    }

    static double sun_equatorial_horizontal_parallax(double r) {
        return 8.794 / (3600.0 * r);
    }

    static void right_ascension_parallax_and_topocentric_dec(double latitude, double elevation,
                                                             double xi, double h, double delta, double delta_alpha, double delta_prime) {
        double delta_alpha_rad;
        double lat_rad = deg2rad(latitude);
        double xi_rad = deg2rad(xi);
        double h_rad = deg2rad(h);
        double delta_rad = deg2rad(delta);
        double u = atan(0.99664719 * tan(lat_rad));
        double y = 0.99664719 * sin(u) + elevation * sin(lat_rad) / 6378140.0;
        double x = cos(u) + elevation * cos(lat_rad) / 6378140.0;

        delta_alpha_rad = atan2(-x * sin(xi_rad) * sin(h_rad),
                cos(delta_rad) - x * sin(xi_rad) * cos(h_rad));

        delta_prime = rad2deg(atan2((sin(delta_rad) - y * sin(xi_rad)) * cos(delta_alpha_rad),
                cos(delta_rad) - x * sin(xi_rad) * cos(h_rad)));

        delta_alpha = rad2deg(delta_alpha_rad);
    }

    static double topocentric_right_ascension(double alpha_deg, double delta_alpha) {
        return alpha_deg + delta_alpha;
    }

    static double topocentric_local_hour_angle(double h, double delta_alpha) {
        return h - delta_alpha;
    }

    static double topocentric_elevation_angle(double latitude, double delta_prime, double h_prime) {
        double lat_rad = deg2rad(latitude);
        double delta_prime_rad = deg2rad(delta_prime);

        return rad2deg(asin(sin(lat_rad) * sin(delta_prime_rad) +
                cos(lat_rad) * cos(delta_prime_rad) * cos(deg2rad(h_prime))));
    }

    static double atmospheric_refraction_correction(double pressure, double temperature,
                                                    double atmos_refract, double e0) {
        double del_e = 0;

        if (e0 >= -1 * (SUN_RADIUS + atmos_refract))
            del_e = (pressure / 1010.0) * (283.0 / (273.0 + temperature)) *
                    1.02 / (60.0 * tan(deg2rad(e0 + 10.3 / (e0 + 5.11))));

        return del_e;
    }

    static double topocentric_elevation_angle_corrected(double e0, double delta_e) {
        return e0 + delta_e;
    }

    static double topocentric_zenith_angle(double e) {
        return 90.0 - e;
    }

    static double topocentric_azimuth_angle_astro(double h_prime, double latitude, double delta_prime) {
        double h_prime_rad = deg2rad(h_prime);
        double lat_rad = deg2rad(latitude);

        return limit_degrees(rad2deg(atan2(sin(h_prime_rad),
                cos(h_prime_rad) * sin(lat_rad) - tan(deg2rad(delta_prime)) * cos(lat_rad))));
    }

    static double topocentric_azimuth_angle(double azimuth_astro) {
        return limit_degrees(azimuth_astro + 180.0);
    }

    static double surface_incidence_angle(double zenith, double azimuth_astro, double azm_rotation,
                                          double slope) {
        double zenith_rad = deg2rad(zenith);
        double slope_rad = deg2rad(slope);

        return rad2deg(acos(cos(zenith_rad) * cos(slope_rad) +
                sin(slope_rad) * sin(zenith_rad) * cos(deg2rad(azimuth_astro - azm_rotation))));
    }

    static double sun_mean_longitude(double jme) {
        return limit_degrees(280.4664567 + jme * (360007.6982779 + jme * (0.03032028 +
                jme * (1 / 49931.0 + jme * (-1 / 15300.0 + jme * (-1 / 2000000.0))))));
    }

    static double eot(double m, double alpha, double del_psi, double epsilon) {
        return limit_minutes(4.0 * (m - 0.0057183 - alpha + del_psi * cos(deg2rad(epsilon))));
    }

    static double approx_sun_transit_time(double alpha_zero, double longitude, double nu) {
        return (alpha_zero - longitude - nu) / 360.0;
    }

    static double sun_hour_angle_at_rise_set(double latitude, double delta_zero, double h0_prime) {
        double h0 = -99999;
        double latitude_rad = deg2rad(latitude);
        double delta_zero_rad = deg2rad(delta_zero);
        double argument = (sin(deg2rad(h0_prime)) - sin(latitude_rad) * sin(delta_zero_rad)) /
                (cos(latitude_rad) * cos(delta_zero_rad));

        if (abs(argument) <= 1) h0 = limit_degrees180(rad2deg(acos(argument)));

        return h0;
    }

    static void approx_sun_rise_and_set(double[] m_rts, double h0) {
        double h0_dfrac = h0 / 360.0;

        m_rts[SUN_RISE] = limit_zero2one(m_rts[SUN_TRANSIT] - h0_dfrac);
        m_rts[SUN_SET] = limit_zero2one(m_rts[SUN_TRANSIT] + h0_dfrac);
        m_rts[SUN_TRANSIT] = limit_zero2one(m_rts[SUN_TRANSIT]);
    }

    static double rts_alpha_delta_prime(double[] ad, double n) {
        double a = ad[JD_ZERO] - ad[JD_MINUS];
        double b = ad[JD_PLUS] - ad[JD_ZERO];

        if (abs(a) >= 2.0) a = limit_zero2one(a);
        if (abs(b) >= 2.0) b = limit_zero2one(b);

        return ad[JD_ZERO] + n * (a + b + (b - a) * n) / 2.0;
    }

    static double rts_sun_altitude(double latitude, double delta_prime, double h_prime) {
        double latitude_rad = deg2rad(latitude);
        double delta_prime_rad = deg2rad(delta_prime);

        return rad2deg(asin(sin(latitude_rad) * sin(delta_prime_rad) +
                cos(latitude_rad) * cos(delta_prime_rad) * cos(deg2rad(h_prime))));
    }

    static double sun_rise_and_set(double[] m_rts, double h_rts[], double delta_prime[], double latitude,
                                   double h_prime[], double h0_prime, int sun) {
        return m_rts[sun] + (h_rts[sun] - h0_prime) /
                (360.0 * cos(deg2rad(delta_prime[sun])) * cos(deg2rad(latitude)) * sin(deg2rad(h_prime[sun])));
    }


}
