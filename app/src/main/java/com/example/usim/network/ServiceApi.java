package com.example.usim.network;

import com.example.usim.data.ContactListResponse;
import com.example.usim.data.IdCheckData;
import com.example.usim.data.IdCheckResponse;
import com.example.usim.data.RecordListResponse;
import com.example.usim.data.SigninData;
import com.example.usim.data.SigninResponse;
import com.example.usim.data.SignupData;
import com.example.usim.data.SignupResponse;
import com.example.usim.data.VisitorCurrentResponse;
import com.example.usim.data.VisitorListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {
    // 로그인
    @POST("/users/signin")
    Call<SigninResponse> userLogin(@Body SigninData data);

    // 회원가입
    @POST("/users/signup")
    Call<SignupResponse> userSignup(@Body SignupData data);

    // 아이디 중복확인
    @POST("/users/id")
    Call<IdCheckResponse> userIdCheck(@Body IdCheckData data);

    // 방문자 리스트
    @GET("/visitors/list")
    Call<VisitorListResponse> visitorList(@Header("token") String token);

    // 실시간 방문자 정보
    @GET("/visitors/current")
    Call<VisitorCurrentResponse> visitorCurrent(
            @Header("token") String token,
            @Query("v_faceId") String v_faceId
            //@Body VisitorCurrentData data
    );
//
//    // 지인 버튼 누르면 방문자 리스트 추가
//    @POST("/visitors/list")
//    Call<VisitorListAppendResponse> postVisitorList(@Body VisitorListAppendData data);

    // 녹음 리스트
    @GET("/records/list")
    Call<RecordListResponse> recordList(@Header("token") String token);

//    @POST("/records/list")
//    Call<RecordListAppendResponse> postRecordList(@Body RecordListAppendData data);

    // 긴급전화 리스트
    @GET("/contacts/list")
    Call<ContactListResponse> contactList(@Header("token") String token);
//    @POST("/contacts/list")
//    Call<ContactListAppendResponse> postContactList(@Body ContactListAppendData data);

}
