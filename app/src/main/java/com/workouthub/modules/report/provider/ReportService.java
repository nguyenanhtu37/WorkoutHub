package com.workouthub.modules.report.provider;

import android.content.Context;

import com.workouthub.entity.report.PracticeReport;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReportService extends IReportService {
    public ReportService(Context context) {
        super(context);
    }

    public DataPoint[] getReportGraphData() {
        List<PracticeReport> reports = repo.getPracticeReport().getAll();
        Collections.sort(reports, new StringAsDateComparator());
        DataPoint[] reportPoints = new DataPoint[reports.size()];
        int i = 0;
        if (reports.size() < 5) {
            reportPoints = new DataPoint[3 + reports.size()];
            for (i = 0; i < 3; i++) {
                reportPoints[i] = new DataPoint(i, 0);
            }
            for (; i < reports.size() + 3; i++) {
                reportPoints[i] = new DataPoint(i, reports.get(i - 3).getCalo());
            }
        } else {
            for (; i < reports.size(); i++) {
                reportPoints[i] = new DataPoint(i, reports.get(i).getCalo());
            }
        }
        return reportPoints;
    }

    class StringAsDateComparator implements Comparator<PracticeReport> {
        public int compare(PracticeReport lhs, PracticeReport rhs) {
            return lhs.getDate().compareTo(rhs.getDate());
        }
    }
}
