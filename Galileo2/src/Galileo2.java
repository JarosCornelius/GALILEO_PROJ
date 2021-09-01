
import org.ejml.dense.row.linsol.svd.SolvePseudoInverseSvd_DDRM;
import org.ejml.dense.row.linsol.svd.SolvePseudoInverseSvd_FDRM;
import org.ejml.simple.SimpleMatrix;

import java.math.BigInteger;
import java.util.Scanner;
import java.lang.*;
import java.lang.Object;
import java.util.Arrays;
import org.ejml.UtilEjml;
import org.ejml.data.*;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.dense.row.NormOps_DDRM;
import org.ejml.equation.Equation;
import org.ejml.ops.MatrixIO;
import org.ejml.simple.ops.*;



public class Galileo2 implements Galileo_Interface {

    public static void main(String[] args) {
        System.out.println("Hello world!"); //HELLO!

        double NumberNanoSecondsPerWeek = 604800.0; //Nano seconds per week
        NumberNanoSecondsPerWeek = NumberNanoSecondsPerWeek * 1000000000; //multiplication, because double was considered as Int
        double NumberNanoSeconds100Mili = 1000000000; // Nano seconds per 100m seconds
        double c = 3*100000000;


        //approx position of the receiver from RINEX file
        double X = 4000000;
        double Y = 1000000;
        double Z = 5000000;


        Satelita satelita1 = new Satelita(); //first satellite
        satelita1.TimeNanos = 1705.442000000; //GNSS receiver’s internal hardware clock value in nanoseconds
        satelita1.TimeNanos = satelita1.TimeNanos * 1000000000; //multiplication, because double was considered as Int
        satelita1.BiasNanos = -0.18840885162353516; //Clock’s sub-nanosecond bias
        satelita1.FullBiasNanos = -1246692066.999178639; //Difference between TimeNanos inside the GPS receiver and the true GPS time since 0000Z, 6 January 1980
        satelita1.FullBiasNanos = satelita1.FullBiasNanos * 1000000000; //multiplication, because double was considered as Int
        satelita1.Ttx = 38954.376636883; //Received GNSS satellite time at the measurement time
        satelita1.Ttx = satelita1.Ttx * 1000000000; //multiplication, because double was considered as Int
        satelita1.TimeOffsetNanos = 0.0; //Time offset at which the measurement was taken in nanoseconds
        satelita1.RinexPseudorange = 23564143.783; //pseudorange taken from RINEX file


        Satelita satelita2 = new Satelita(); //second satellite
        satelita2.TimeNanos = 1705.442000000;
        satelita2.TimeNanos = satelita2.TimeNanos * 1000000000;
        satelita2.BiasNanos = -0.18840885162353516;
        satelita2.FullBiasNanos = -1246692066.999178639;
        satelita2.FullBiasNanos = satelita2.FullBiasNanos * 1000000000;
        satelita2.Ttx = 200972.367072045;
        satelita2.Ttx = satelita2.Ttx * 1000000000;
        satelita2.TimeOffsetNanos = 0.0;
        satelita2.RinexPseudorange = 23890236.134;


        Satelita satelita3 = new Satelita(); //third satellite
        satelita3.TimeNanos = 1705.442000000;
        satelita3.TimeNanos = satelita3.TimeNanos * 1000000000;
        satelita3.BiasNanos = -0.18840885162353516;
        satelita3.FullBiasNanos = -1246692066.999178639;
        satelita3.FullBiasNanos = satelita3.FullBiasNanos * 1000000000;
        satelita3.Ttx = 200958.308567398;
        satelita3.Ttx = satelita3.Ttx * 1000000000;
        satelita3.TimeOffsetNanos = 0.0;
        satelita3.RinexPseudorange = 23747778.355;


        Satelita satelita4 = new Satelita(); //fourth satellite
        satelita4.TimeNanos = 1705.442000000;
        satelita4.TimeNanos = satelita4.TimeNanos * 1000000000;
        satelita4.BiasNanos = -0.18840885162353516;
        satelita4.FullBiasNanos = -1246692066.999178639;
        satelita4.FullBiasNanos = satelita4.FullBiasNanos * 1000000000;
        satelita4.Ttx = 200972.362773138;
        satelita4.Ttx = satelita4.Ttx * 1000000000;
        satelita4.TimeOffsetNanos = 0.0;
        satelita4.RinexPseudorange = 25213812.641;


        //new Galileo2 class objects
        Galileo2 matrix = new Galileo2();
        Galileo2 pseudorange1 = new Galileo2();
        Galileo2 pseudorange2 = new Galileo2();
        Galileo2 pseudorange3 = new Galileo2();
        Galileo2 pseudorange4 = new Galileo2();


        //calling the methods to callculate pseudorange
        double GalileoTime1 = pseudorange1.GalileoTime(satelita1.TimeNanos, satelita1.FullBiasNanos, satelita1.BiasNanos);
        double weekNumberNanos1 = pseudorange1.weekNumberNanos(satelita1.FullBiasNanos, NumberNanoSecondsPerWeek);
        double trxGNSS1 = pseudorange1.trxGNSS(satelita1.TimeNanos, satelita1.TimeOffsetNanos, satelita1.FullBiasNanos, satelita1.BiasNanos);
        double TOW1st1 = pseudorange1.trxTowDecoded1(trxGNSS1, NumberNanoSecondsPerWeek);
        double TOW1nd2 = pseudorange1.trxTowDecoded2(trxGNSS1, weekNumberNanos1);
        double E1C2nd1 = pseudorange1.trxE1C2nd(trxGNSS1, NumberNanoSeconds100Mili);
        double ps1st = pseudorange1.pseudorange_counter(TOW1st1, E1C2nd1, satelita1.Ttx, NumberNanoSeconds100Mili, c);
        double ps1nd = pseudorange1.pseudorange_counter(TOW1nd2, E1C2nd1, satelita1.Ttx, NumberNanoSeconds100Mili, c);


        double GalileoTime2 = pseudorange2.GalileoTime(satelita2.TimeNanos, satelita2.FullBiasNanos, satelita2.BiasNanos);
        double weekNumberNanos2 = pseudorange2.weekNumberNanos(satelita2.FullBiasNanos,  NumberNanoSecondsPerWeek);
        double trxGNSS2 = pseudorange2.trxGNSS(satelita2.TimeNanos, satelita2.TimeOffsetNanos, satelita2.FullBiasNanos, satelita2.BiasNanos);
        double TOW2st1 = pseudorange2.trxTowDecoded1(trxGNSS2, NumberNanoSecondsPerWeek);
        double TOW2nd2 = pseudorange2.trxTowDecoded2(trxGNSS2, weekNumberNanos2);
        double E1C2nd2 = pseudorange2.trxE1C2nd(trxGNSS2, NumberNanoSeconds100Mili);
        double ps2st = pseudorange2.pseudorange_counter(TOW2st1, E1C2nd2, satelita2.Ttx, NumberNanoSeconds100Mili, c);
        double ps2nd = pseudorange2.pseudorange_counter(TOW2nd2, E1C2nd2, satelita2.Ttx, NumberNanoSeconds100Mili, c);


        double GalileoTime3 = pseudorange3.GalileoTime(satelita3.TimeNanos, satelita3.FullBiasNanos, satelita3.BiasNanos);
        double weekNumberNanos3 = pseudorange3.weekNumberNanos(satelita3.FullBiasNanos,  NumberNanoSecondsPerWeek);
        double trxGNSS3 = pseudorange3.trxGNSS(satelita3.TimeNanos, satelita3.TimeOffsetNanos, satelita3.FullBiasNanos, satelita3.BiasNanos);
        double TOW3st1 = pseudorange3.trxTowDecoded1(trxGNSS3, NumberNanoSecondsPerWeek);
        double TOW3nd2 = pseudorange3.trxTowDecoded2(trxGNSS3, weekNumberNanos3);
        double E1C2nd3 = pseudorange3.trxE1C2nd(trxGNSS3, NumberNanoSeconds100Mili);
        double ps3st = pseudorange3.pseudorange_counter(TOW3st1, E1C2nd3, satelita3.Ttx, NumberNanoSeconds100Mili, c);
        double ps3nd = pseudorange3.pseudorange_counter(TOW3nd2, E1C2nd3, satelita3.Ttx, NumberNanoSeconds100Mili, c);


        double GalileoTime4 = pseudorange4.GalileoTime(satelita4.TimeNanos, satelita4.FullBiasNanos, satelita4.BiasNanos);
        double trxGNSS4 = pseudorange4.trxGNSS(satelita4.TimeNanos, satelita4.TimeOffsetNanos, satelita4.FullBiasNanos, satelita4.BiasNanos);
        double weekNumberNanos4 = pseudorange4.weekNumberNanos(satelita4.FullBiasNanos,  NumberNanoSecondsPerWeek);
        double TOW4st1 = pseudorange4.trxTowDecoded1(trxGNSS4, NumberNanoSecondsPerWeek);
        double TOW4nd2 = pseudorange4.trxTowDecoded2(trxGNSS4, weekNumberNanos4);
        double E1C2nd4 = pseudorange4.trxE1C2nd(trxGNSS4, NumberNanoSeconds100Mili);
        double ps4st = pseudorange4.pseudorange_counter(TOW4st1, E1C2nd4, satelita4.Ttx, NumberNanoSeconds100Mili, c);
        double ps4nd = pseudorange4.pseudorange_counter(TOW4nd2, E1C2nd4, satelita4.Ttx, NumberNanoSeconds100Mili, c);


        System.out.println("PSEUDORANGE FISRT CONSTELLATION, WITH FIRST METHOD: " + ps1st); //pseudorange calculated with the first method
        System.out.println("PSEUDORANGE FISRT CONSTELLATION, WITH SECOND METHOD: " + ps1nd); //pseudorange calculated with the second method
        System.out.println("PSEUDORANGE SECOND CONSTELLATION, WITH FIRST METHOD: " + ps2st);
        System.out.println("PSEUDORANGE SECOND CONSTELLATION, WITH SECOND METHOD: " + ps2nd);
        System.out.println("PSEUDORANGE THIRD CONSTELLATION, WITH FIRST METHOD: " + ps3st);
        System.out.println("PSEUDORANGE THIRD CONSTELLATION, WITH SECOND METHOD: " + ps3nd);
        System.out.println("PSEUDORANGE FOURTH CONSTELLATION, WITH FIRST METHOD: " + ps4st);
        System.out.println("PSEUDORANGE FOURTH CONSTELLATION, WITH SECOND METHOD: " + ps4nd);


        //creating the pseudorange matrix
        double pseudorangematrix[][] = {{satelita1.RinexPseudorange - ps1st},
                                        {satelita2.RinexPseudorange - ps2st},
                                        {satelita3.RinexPseudorange - ps3st},
                                        {satelita4.RinexPseudorange - ps4st}};
        matrix.printPseudorangeMatrix(pseudorangematrix);


        //creating the approx position of the receiver matrix
        double approxpositionofthereceiverMatrix[][] = {{X}, {Y}, {Z}, {0}};
        matrix.printapproxpositionofthereceiverMatrix(approxpositionofthereceiverMatrix);


        //creating the A matrix of the increments
        double AMatrix[][] = {{1, 2, 3, 4}, {1, 5, 3, 1}, {1, 2, 9, 1}, {5, 2, 3, 1}};
        matrix.printAMatrix(AMatrix);


        double transpose[][] = new double[4][4]; //additional matrix to transponse matrix A
        double transponted[][] = matrix.transponse(transpose, AMatrix); //A matrix after transponse


        //multiplication of the A matrix and transponted A matrix
        double AMultipilaction[][] = matrix.matrixMultiplication(transponted, AMatrix);
        matrix.showMultiplication(AMultipilaction);


        //Matrix inversion using library ejml
        System.out.println("Couting inverted matrix: ");
        SimpleMatrix i = new SimpleMatrix(AMultipilaction);
        SimpleMatrix inverted = i.invert();
        System.out.println(inverted);


        //new SimpleMatrix class objects
        SimpleMatrix transpontedejml = new SimpleMatrix(transponted);
        SimpleMatrix pseudorangesolutionM = new SimpleMatrix(pseudorangematrix);
        SimpleMatrix approxPosition = new SimpleMatrix(approxpositionofthereceiverMatrix);



        System.out.println("Couting dx matrix: ");

        //multiplication of the inverted and transponted A matrix
        SimpleMatrix cMatrix =  inverted.mult(transpontedejml);

        //multiplication of the upper and pseudorange matrix
        cMatrix = cMatrix.mult(pseudorangesolutionM);
        System.out.println(cMatrix);


        System.out.println("couting posistion of the receiver: ");

        //addition of the approximate position and upper counted matrix
        SimpleMatrix dMatrix = approxPosition.plus(cMatrix);
        System.out.println(dMatrix);

    }


    //Galileo Time counter
    @Override
    public double GalileoTime(double TimeNanos, double FullBiasNanos, double Biasnanos) {
        double GalileoTime = (TimeNanos - (FullBiasNanos + Biasnanos));
        System.out.println("GalileoTime: " + GalileoTime);
        return GalileoTime;
    }


    //Trxgnss measurement time in full GNSS time counter
    @Override
    public double trxGNSS(double TimeNanos, double TimeOffsetNanos, double FullBiasNanos, double BiasNanos) {
        double TrxGNSS = TimeNanos + TimeOffsetNanos - (FullBiasNanos + BiasNanos);
        System.out.println("TrxGNSS: " + TrxGNSS);
        return TrxGNSS;
    }


    //Trx measurment time for Galileo with TOW decoded counter, first method
    @Override
    public double trxTowDecoded1(double TrxGNSS, double NumberNanoSecondsPerWeek) {
        double trx = TrxGNSS % NumberNanoSecondsPerWeek;
        System.out.println("trx TOW1: " + trx);
        return trx;
    }


    //Trx measurment time for Galileo with TOW decoded counter, second method
    @Override
    public double trxTowDecoded2(double TrxGNSS, double weekNumberNanos) {
        double trx = TrxGNSS - weekNumberNanos;
        System.out.println("trx TOW2: " + trx);
        return trx;
    }


    //couting the number of nanoseconds that have occured from the beginning of GPS time to the current WN
    @Override
    public double weekNumberNanos(double FullBiasNanos, double NumberNanoSecondsPerWeek) {
        double Nanos = Math.floor((-FullBiasNanos) / NumberNanoSecondsPerWeek) * NumberNanoSecondsPerWeek;
        System.out.println("weekNumberNanos: " + Nanos);
        return Nanos;
    }


    //Trx measurment time for Galileo with E1C2nd decoded counter
    @Override
    public double trxE1C2nd(double TrxGNSS, double NumberNanoSeconds100Mili) {
        double trx = TrxGNSS % NumberNanoSeconds100Mili;
        System.out.println("trx E1C2nd: " + trx);
        return trx;
    }


    //couting the pseudorange in meters using TOW or E1C2nd Trx, if Ttx is bigger than 100mili, than TOW Trx is used
    @Override
    public double pseudorange_counter(double tRX1, double tRX2, double tTX, double NumberNanoSeconds100Mili, double c) {
        System.out.println("TOW if Ttx is bigger than 100milisec");

        double p;
        if (tTX > NumberNanoSeconds100Mili)
            p = ((tRX1 - tTX) / 1000000000) * c;
        else
            p = ((tRX2 - tTX) / 1000000000) * c;

        System.out.println("pseudorange: " + p);
        return p;
    }


    //printing the pseudorange matrix
    @Override
    public void printPseudorangeMatrix(double pseudorangematrix[][]) {

        System.out.println("Printing Matrix of pseudorange:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.print(pseudorangematrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    //printing the approx position of the receiver matrix
    @Override
    public void printapproxpositionofthereceiverMatrix(double approxpositionofthereceiver[][]) {

        System.out.println("Printing Matrix of approx receiver position:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.print(approxpositionofthereceiver[i][j] + " ");
            }
            System.out.println();
        }
    }


    //printing the A matrix of the increments
    @Override
    public void printAMatrix(double AMatrix[][]) {
        System.out.println("Printing A Matrix:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(AMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    //matrix transponse counter
    @Override
    public double[][] transponse(double transponse[][], double AMatrix[][]) {
        System.out.println("Matrix A after transponse");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                transponse[i][j] = AMatrix[j][i];
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(transponse[i][j] + " ");
            }
            System.out.println();
        }
        return transponse;
    }


    //matrix multiplication counter
    @Override
    public double[][] matrixMultiplication(double tranponted[][], double AMatrix[][]) {

        double[][] Multiplicated = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Multiplicated[i][j] = 0;
                for (int k = 0; k < 4; k++) {
                    Multiplicated[i][j] += tranponted[i][k] * AMatrix[k][j];
                }
            }
        }
        return Multiplicated;
    }


    //printing the multiplicated matrix
    @Override
    public void showMultiplication(double AMultipication[][]) {
        System.out.println("Printing Multiplicated A Matrix:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(AMultipication[i][j] + " ");
            }
            System.out.println();
        }
    }
}

