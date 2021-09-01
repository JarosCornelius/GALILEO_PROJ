public class Satelita extends Galileo2 {

    double Ttx; //Received GNSS satellite time at the measurement time
    double TimeNanos; //GNSS receiver’s internal hardware clock value in nanoseconds
    double TimeOffsetNanos; //Time offset at which the measurement was taken in nanoseconds
    double FullBiasNanos; //Difference between TimeNanos inside the GPS receiver and the true GPS time since 0000Z, 6 January 1980
    double BiasNanos; //Clock’s sub-nanosecond bias
    double RinexPseudorange;

}
