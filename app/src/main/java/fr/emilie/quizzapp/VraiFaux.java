package fr.emilie.quizzapp;

/**
 * Created by Emilie on 06/12/2017.
 */

public class VraiFaux {

    //variables
    private int mQuestionID;
    private boolean mVraiReponse;

    /**
     *
     * @param mQuestionID
     * @param mVraiReponse
     */
    public VraiFaux(int mQuestionID, boolean mVraiReponse){
        this.mQuestionID = mQuestionID;
        this.mVraiReponse = mVraiReponse;
    }

    /**
     *
     * @return mQuestion
     */
    public int getQuestionID(){
        return mQuestionID;
    }

    /**
     *
     * @return mVraiReponse
     */
    public boolean getReponse(){
        return mVraiReponse;
    }


}
