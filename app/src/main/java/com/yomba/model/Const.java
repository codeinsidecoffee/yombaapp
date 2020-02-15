package com.yomba.model;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class Const {

    public static final String BASE_URL ="https://yomba.jodhaa.co.in/";
    public static final String Verify_Code_FILE_NAME="users/api/verify/code";
    public static final String Verify_Email_FILE_NAME="users/api/verify/email";
    public static final String Verify_OTPCode_FILE_NAME="users/api/verify/valid_code";
    public static final String Verify_Saved_Data_FILE_NAME="users/api/verify/savedata";
    public static final String Verify_Phone_FILE_NAME="users/api/verify/phone";
    public static final String Invitee_List_FILE_NAME="users/api/verify/friendlist";
    public static final String Country_List_FILE_NAME="admin/api/country";
    public static final String START_GAME_FILE_NAME ="admin/api/task/start_game";
    public static final String TASK_STATUS_FILE_NAME ="admin/api/task/status";
    public static final String Task_Ans_FILE_NAME="admin/api/task/verify";
    public static final String FETCH_CLUE_FILE_NAME ="admin/api/task/open_clue";
    public static final String Open_Solve_FILE_NAME="admin/api/task/open_solve";
    public static final String Logout_Game_FILE_NAME="admin/api/task/logout_game";
    public static final String Summary_FILE_NAME="admin/api/task/summary";

    public static final String REG_CODE ="registration_code";
    public static final String EMAIL ="email";
    public static final String PHONE ="phone";
    public static final String ORDER_ID ="order_id";
    public static final String BUYER ="buyer";
    public static final String INVITEE ="invitee";
    public static final String TRUE ="true";
    public static final String FALSE ="false";
    public static final String VERIFICATION_TYPE ="verification_type";
    public static final String VERIFICATION_CODE ="verification_code";

    public static final String SCENARIO ="scenario";
    public static final String FIRST_NAME ="first_name";
    public static final String LAST_NAME="last_name";
    public static final String NICK_NAME="nickname";
    public static final String LOGIN_ID ="login_id";
    public static final String GAME_ID ="game_id";
    public static final String BACK ="Back";
    public static final String PREF_FILE ="prefbean";
    public static final String USER_ID ="user_id";
    public static final String PLAYER_IDS ="player_ids";
    public static final String PLAY_ID ="play_id";
    public static final String A ="A";
    public static final String B ="B";
    public static final String C ="C";
    public static final String D ="D";
    public static final String DEVICE_TOKEN ="device_token";
    public static final String TASK_ID ="task_id";
    public static final String ANSWER ="answer";
    public static final String SKIP ="skip";
    public static final String ANSWER_TYPE ="answer_type";
    public static final String BUYER_NICKNAME ="BuyerNickName";
    public static final String BUYER_EMAIL ="BuyerEmail";
    public static final String PLAIN ="plain";
    public static final String CLOCK ="clock";
    public static final String LANGUAGE ="language";
    public static ByteArrayOutputStream BOS =null;
    public static final String GAME_COMPLETED_ALERT ="game_completed";
    public static final String TASK_STATUS_ALERT ="task_status";
    public static final String OPEN_CLUE_ALERT ="open_clue";

    public static TaskInfoBean.Logininfo LOGIN_INFO =new TaskInfoBean.Logininfo();


    public static UserInfo buyerInfo;
    public static UserInfo userInfo;
    public static InviteeListBean.Gameinfo GAME_INFO;


    public static String currentUserType="";

    public static String CurrentLang ="CurrentLang";
    public static String currentAction="";
    public static String FireBaseTokenID="";

    public static String SelectedFileName="selectedfilename";
    public static TaskInfoBean currentTASKInfo=null;
    public static String Notification_Category="";
    public static String isPlayGame="isGameFinish";
    public static String isStartGame="isGameStarted";
    public static String isWaitForGame="isWaitForGame";
    public static String isInviteeScreen="isInviteeScreen";
    public static String Wrong_Answer="That's Wrong!";
    public static String Right_Answer="You're Amazing!";
    public static String isGameCompleted="false";
    public static String canBeSkipped="false";
    public static int TotalClue=3;
    public static boolean visitedAboutGame=false;
    public static int countryPosition=103;
    public static boolean InternetNotFound=false;
    public static List<String> playerIDs;


    public static Long RemainingClue=0L;
}
