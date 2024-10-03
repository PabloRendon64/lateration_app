package com.lateration_app.example.domain.service;

import com.lateration_app.example.domain.exception.LevelThreeValidationException;
import com.lateration_app.example.domain.model.Satellite;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import sw.trilateration.NonLinearLeastSquaresSolver;
import sw.trilateration.TrilaterationFunction;

import java.util.Arrays;
import java.util.List;

public class LevelOneService {

    public static String getMessage(String[]... messages) {
        String[] result = messages[0];
        for (int i = 1; i < messages.length; i++) {
            String[] next = messages[i];
            for (int j = 0; j < next.length; j++) {
                if("".equals(result[j]) && !"".equals(next[j])) {
                    result[j] = next[j];
                }
            }
        }
        if(hasEmpty(result)) {
            throw new LevelThreeValidationException("no hay suficiente información");
        }
        return String.join(" ", result);
    }

    public static double[] getLocation(double[][] positions, double... distances) {
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
                new TrilaterationFunction(positions, distances),
                new LevenbergMarquardtOptimizer());
        Optimum optimum = solver.solve();
        return optimum.getPoint().toArray();
    }

    public static double[] getLocation(List<Satellite> satellites) {
        double[][] positions = new double[satellites.size()][1];
        double[] distances = new double[satellites.size()];
        for (int i = 0; i < satellites.size(); i++) {
            positions[i] = new double[satellites.size()];
            Satellite satellite = satellites.get(i);
            positions[i][0] = satellite.getPositionX();
            positions[i][1] = satellite.getPositionY();
            distances[i] = satellite.getDistance();
        }
        return getLocation(positions, distances);
    }

    public static String getMessage(List<Satellite> satellites) {
        String[][] messages = new String[satellites.size()][];
        for (int i = 0; i < satellites.size(); i++) {
            messages[i] = satellites.get(i).getMessage();
        }
        return getMessage(messages);
    }

    private static boolean hasEmpty(String[] message) {
        return Arrays.stream(message).anyMatch(String::isEmpty);
    }

}
