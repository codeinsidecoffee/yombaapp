package com.yomba.dao;

import com.yomba.model.ClueBean;
import com.yomba.model.Const;
import com.yomba.model.CountryBean;
import com.yomba.model.InviteeListBean;
import com.yomba.model.SummaryBean;
import com.yomba.model.TaskInfoBean;
import com.yomba.model.UserInfo;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface RetroFitService {

    @FormUrlEncoded
    @POST(Const.Verify_Code_FILE_NAME)
    Call<UserInfo> verifyCode(@Field(Const.REG_CODE) String reg_code);

    @FormUrlEncoded
    @POST(Const.Verify_Email_FILE_NAME)
    Call<UserInfo> verifyEmail(@Field(Const.EMAIL) String email,
                               @Field(Const.ORDER_ID) String order_id,
                               @Field(Const.LANGUAGE) String language);


    @FormUrlEncoded
    @POST(Const.Verify_OTPCode_FILE_NAME)
    Call<UserInfo> verifyOTPCode(@Field(Const.VERIFICATION_TYPE) String verification_type,
                                 @Field(Const.VERIFICATION_CODE) String verification_code,
                                 @Field(Const.SCENARIO) String scenario,
                                 @Field(Const.LOGIN_ID) String login_id,
                                 @Field(Const.ORDER_ID) String order_id,
                                 @Field(Const.DEVICE_TOKEN) String device_token);


    @FormUrlEncoded
    @POST(Const.Verify_Saved_Data_FILE_NAME)
    Call<UserInfo> verifyInfo(@Field(Const.SCENARIO) String scenario,
                              @Field(Const.FIRST_NAME) String first_name,
                              @Field(Const.LAST_NAME) String last_name,
                              @Field(Const.NICK_NAME) String nickname,
                              @Field(Const.LOGIN_ID) String login_id,
                              @Field(Const.ORDER_ID) String order_id);

    @FormUrlEncoded
    @POST(Const.Verify_Phone_FILE_NAME)
    Call<UserInfo> verifyPhone(@Field(Const.PHONE) String phone,
                               @Field(Const.SCENARIO) String scenario,
                               @Field(Const.LOGIN_ID) String login_id,
                               @Field(Const.ORDER_ID) String order_id);


    @FormUrlEncoded
    @POST(Const.Invitee_List_FILE_NAME)
    Call<InviteeListBean> inviteeList(@Field(Const.ORDER_ID) String order_id,
                                      @Field(Const.GAME_ID) String game_id);

    @FormUrlEncoded
    @POST(Const.START_GAME_FILE_NAME)
    Call<TaskInfoBean> startGame(@Field(Const.LOGIN_ID) String login_id,
                                 @Field(Const.USER_ID) String user_id,
                                 @Field(Const.GAME_ID) String game_id,
                                 @Field(Const.PLAYER_IDS) String player_ids);

    @FormUrlEncoded
    @POST(Const.TASK_STATUS_FILE_NAME)
    Call<TaskInfoBean> taskStatus(@Field(Const.LOGIN_ID) String login_id,
                                  @Field(Const.USER_ID) String user_id,
                                  @Field(Const.GAME_ID) String game_id,
                                  @Field(Const.PLAY_ID) String play_id,
                                  @Field(Const.SCENARIO) String scenario);

    @FormUrlEncoded
    @POST(Const.Country_List_FILE_NAME)
    Call<CountryBean> countryList(@Field(Const.LOGIN_ID) String login_id);

    @POST(Const.Task_Ans_FILE_NAME)
    Call<TaskInfoBean>  verifyTaskAns(@Body RequestBody file);

    @FormUrlEncoded
    @POST(Const.FETCH_CLUE_FILE_NAME)
    Call<ClueBean> fetchClue(@Field(Const.LOGIN_ID) String login_id,
                             @Field(Const.USER_ID) String user_id,
                             @Field(Const.PLAY_ID) String play_id,
                             @Field(Const.TASK_ID) String task_id,
                             @Field(Const.SCENARIO) String scenario);

    @FormUrlEncoded
    @POST(Const.Open_Solve_FILE_NAME)
    Call<UserInfo> openSolve(@Field(Const.PLAY_ID) String play_id,
                             @Field(Const.TASK_ID) String task_id);
    @FormUrlEncoded
    @POST(Const.Logout_Game_FILE_NAME)
    Call<UserInfo> logoutGame(@Field(Const.SCENARIO) String scenario,
                             @Field(Const.ORDER_ID) String order_id,
                             @Field(Const.LOGIN_ID) String login_id);

    @FormUrlEncoded
    @POST(Const.Summary_FILE_NAME)
    Call<SummaryBean> fetchSummary(@Field(Const.PLAY_ID) String playid,
                                 @Field(Const.USER_ID) String userid,
                                 @Field(Const.SCENARIO) String scenario);
}
