import org.ejml.simple.SimpleMatrix;

public interface Galileo_Interface
{
    double GalileoTime(double TimeNanos,double FullBiasNanos,double Biasnanos); //Galileo Time counter
    double trxGNSS(double TimeNanos, double TimeOffsetNanos, double FullBiasNanos, double BiasNanos); //Trxgnss measurement time in full GNSS time counter
    double trxTowDecoded1(double trGNSS, double NumberNanoSecondsPerWeek); //Trx measurment time for Galileo with TOW decoded counter, first method
    double trxTowDecoded2(double trGNSS, double weekNumberNanos); //Trx measurment time for Galileo with TOW decoded counter, second method
    double trxE1C2nd(double trGNSS, double NumberNABO100Mili); //Trx measurment time for Galileo with E1C2nd decoded counter
    double pseudorange_counter(double tRX1, double tRX2, double tTX,  double NumberNanoSeconds100Mili, double c);  //couting the pseudorange in meters using TOW or E1C2nd Trx, if Ttx is bigger than 100mili, than TOW Trx is used
    double weekNumberNanos(double FullBiasNanos, double  NumberNanoSecondsPerWeek); //couting the number of nanoseconds that have occured from the beginning of GPS time to the current WN
    void printPseudorangeMatrix(double pseudorangematrix[][]); //printing the pseudorange matrix
    void printapproxpositionofthereceiverMatrix(double approxpositionofthereceiver[][]); //printing the approx position of the receiver matrix
    void printAMatrix(double AMatrix[][]); //printing the A matrix of the increments
    double[][] transponse(double transponse[][], double AMatrix[][]); //matrix transponse counter
    double[][] matrixMultiplication(double tranponted[][], double AMatrix[][]); //matrix multiplication counter
    void showMultiplication(double MatrixAMultiplication[][]); //printing the multiplicated matrix
}
