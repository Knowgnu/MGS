package com.icia.mgs.controller;

import com.icia.mgs.dto.*;
import com.icia.mgs.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     // @Responsebody 생략가능!!
@RequiredArgsConstructor
public class RestfulController {
    private final MemberService msvc;
    private final CompanyService csvc;

    // 요청 서비스
    private final RequestService rqsvc;
    private final ProductService psvc;
    private final SaleService ssvc;
    private final PSurveyService svsvc;

    // 아이디 중복확인
    @PostMapping("idCheck")
    public String idCheck(@RequestParam("mId") String mId){
        return msvc.idCheck(mId);
    }

    // 카카오 이메일 중복확인
    @PostMapping("/oauth/emailCheck2")
    public String emailCheck2(@RequestParam("mEmail") String mEmail){
        return msvc.emailCheck2(mEmail);
    }
    // 일반 이메일 중복확인
    @PostMapping("emailCheck3")
    public String emailCheck3(@RequestParam("mEmail") String mEmail){
        return msvc.emailCheck3(mEmail);
    }
    // 카카오 인증 아이디체크
    @PostMapping("/oauth/idCheck2")
    public String idCheck2(@RequestParam("mId") String mId){
        return msvc.idCheck2(mId);
    }

    // 이메일 인증
    @PostMapping("emailCheck")
    public String emailCheck(@RequestParam("mEmail") String mEmail){
        return msvc.emailCheck(mEmail);
    }
    // 회원목록(페이징)
    @PostMapping("pagingList")
    public List<MemberDTO> pagingList(){
        return msvc.pagingList();
    }
    // 회원검색
    @PostMapping("searchList")
    public List<MemberDTO> searchList(@ModelAttribute SearchDTO search){
        return msvc.searchList(search);
    }
    // 회사목록(페이징)
    @PostMapping("cPagingList")
    public List<CompanyDTO> cPagingList(){
        return csvc.cPagingList();
    }
    // 회사검색
    @PostMapping("cSearchList")
    public List<CompanyDTO> cSearchList(@ModelAttribute SearchDTO search){
        return csvc.cSearchList(search);
    }

    // 상품목록(페이징)
    @PostMapping("pPagingList")
    public List<ProductDTO> pPagingList(){
        return psvc.pPagingList();
    }

    // 상품검색
    @PostMapping("pSearchList")
    public List<ProductDTO> pPagingList(@ModelAttribute SearchDTO search){
        return psvc.pSearchList(search);
    }

    // 상품목록(페이징)
    @PostMapping("sPagingList")
    public List<SaleDTO> sPagingList(){
        return ssvc.sPagingList();
    }
    // sPagingList2 버튼으로 상품목록
    @PostMapping("sPagingList2")
    public List<SaleDTO> sPagingList2(@RequestParam int type){
        return ssvc.sPagingList2(type);
    }
    // rqPagingList 요청페이징리스트
    @PostMapping ("rqPagingList")
    public List<RequestDTO> rqPagingList(){
        return rqsvc.rqPagingList();
    }
    @GetMapping ("/rqPagingList2")
    public List<RequestDTO> rqPagingList2(@RequestParam String mId){
        System.out.println("List2 : " + mId);
        return rqsvc.rqPagingList2(mId);
    }

    // rqSearchList 요청 페이징 검색 System.out.println("Controller : " + search);
    @PostMapping("rqSearchList")
    public List<RequestDTO> rqSearchList(@ModelAttribute SearchDTO search){
        System.out.println("Controller : " + search);
        return rqsvc.rqSearchList(search);
    }

    @PostMapping("/rqSearchList2")
    public List<RequestDTO> rqSearchList2(@ModelAttribute SearchDTO search, @RequestParam String mId){
        System.out.println("Controller : " + search);
        return rqsvc.rqSearchList2(search,mId);
    }

    // 상품검색
    @PostMapping("sSearchList")
    public List<SaleDTO> sPagingList(@ModelAttribute SearchDTO search){
        return ssvc.sSearchList(search);
    }

    // likeup 좋아요
    @PostMapping("/likeup")
    public int likeup(@RequestParam int sNum , @RequestParam String mId , @RequestParam int pNum){
        System.out.println("likeup Contorller : " + sNum + "," + mId + " , " + pNum);
        return ssvc.likeup(sNum,mId,pNum);
    }
    // likedown 좋아요 취소
    @PostMapping("/likedown")
    public int likedown(@RequestParam int sNum , @RequestParam String mId , @RequestParam int pNum){
        System.out.println("likedown Controller : " + sNum + "," + mId + " , " + pNum);
        return ssvc.likedown(sNum,mId,pNum);
    }

    // svPagingList 인원조사 게시판
    @PostMapping("psPagingList")
    public List<PSurveyDTO> psPagingList(){
        return svsvc.psPagingList();
    }
    // psSearchList 인원조사 검색
    @PostMapping("psSearchList")
    public List<PSurveyDTO> psSearchList(@ModelAttribute SearchDTO search){
        return svsvc.psSearchList(search);
    }

    // svWrite 인원조사 신청하기
    @GetMapping("/svWrite")
    public int svWrite(@RequestParam int psNum, @RequestParam String mId , @RequestParam int svCount){
        System.out.println("svWrite Controller : " + psNum + " , " + mId + " , " + svCount);
        return svsvc.svWrite(psNum,mId,svCount);
    }
    // /svCancel 인원조사 신청취소
    @GetMapping("/svCancel")
    public int svCancel(@RequestParam int psNum, @RequestParam String mId){
        System.out.println("svCancel Controller : " + psNum + " , " + mId);
        return svsvc.svCancel(psNum,mId);
    }
    // plPagingList 좋아요리스트
    @PostMapping("/plPagingList")
    public List<ProductEntity> plPagingList(@RequestParam String mId){
        System.out.println("plList Controller : " + mId);
        return ssvc.plPaginList(mId);
    }

    // 신청참여자 목록 (어드민)
    @PostMapping("/psmPagingList")
    public List<SvPeopleDTO> psmPagingList(@RequestParam int psNum){
        System.out.println("psmList : " + psNum);{
            return svsvc.psmPagingList(psNum);
        }
    }

    // 신청 잠여자 목록 회원본인
    @PostMapping("/psmPagingList2")
    public List<PSurveyDTO> psmPagingList2(@RequestParam String mId){
        System.out.println("psmList2 : " + mId);
        return svsvc.psmPagingList2(mId);
    }

}
