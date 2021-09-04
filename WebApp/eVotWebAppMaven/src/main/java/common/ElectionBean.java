package common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ElectionBean extends ElectionResultsBean {

    private Category mCategory;
    private String mLocalitate;
    private String mJudet;
    private LocalDate mStartingDate;
    private LocalDate mEndingDate;
    private int mCandidatesCount;
    private int mOldCandidatesCount;
    public boolean isNational;
    public boolean isLocal;
    public boolean isCounty;

    public ElectionBean() {
        super();
        mStartingDate = null;
        mEndingDate = null;
        mLocalitate = "";
        mJudet = "";
        mCategory = new Category();
        mCandidatesCount = 0;
        mOldCandidatesCount = 0;
        isNational = false;
        isLocal = false;
        isCounty = false;
    }

    public void resetCategoryState() {
        isLocal = false;
        isCounty = false;
        isNational = false;
    }
    
    public void dropCandidates() {
        mCandidatesArray.clear();
    }

    public int getCandidatesCount() {
        return mCandidatesCount;
    }

    public void setCandidatesCount(int mCandidatesCount) {
        this.mCandidatesCount = mCandidatesCount;
    }

    public String getLocalitate() {
        return mLocalitate;
    }

    public void setLocalitate(String mLocalitate) {
        this.mLocalitate = mLocalitate;
    }

    public String getJudet() {
        return mJudet;
    }

    public void setJudet(String mJudet) {
        this.mJudet = mJudet;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category mCategory) {
        this.mCategory = mCategory;
    }

    public LocalDate getStartingDate() {
        return mStartingDate;
    }

    public void setStartingDate(LocalDate mStartingDate) {
        this.mStartingDate = mStartingDate;
    }

    public LocalDate getEndingDate() {
        return mEndingDate;
    }

    public void setEndingDate(LocalDate mEndingDate) {
        this.mEndingDate = mEndingDate;
    }

    public void setStartingDate(String date) {
        if (date.isEmpty()) {
            mStartingDate = null;
        }
        date = date.replace('/', '-');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        mStartingDate = LocalDate.parse(date, formatter);
    }

    public void setEndingDate(String date) {
        if (date.isEmpty()) {
            mEndingDate = null;
        }
        date = date.replace('/', '-');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        mEndingDate = LocalDate.parse(date, formatter);
    }

    public void setOldCandidatesCount(int i) {
        mOldCandidatesCount = i;
    }

    public int getOldCandidatesCount() {
        return mOldCandidatesCount;
    }
}
