package shop.hooking.hooking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.hooking.hooking.config.BrandType;
import shop.hooking.hooking.config.MoodType;
import shop.hooking.hooking.dto.CardSearchCondition;
import shop.hooking.hooking.dto.HttpRes;
import shop.hooking.hooking.dto.request.CopyReq;
import shop.hooking.hooking.dto.response.CopyRes;
import shop.hooking.hooking.entity.Card;
import shop.hooking.hooking.entity.User;
import shop.hooking.hooking.exception.BadRequestException;
import shop.hooking.hooking.repository.CardJpaRepository;
import shop.hooking.hooking.repository.CardRepository;
import shop.hooking.hooking.service.BrandService;
import shop.hooking.hooking.service.CopyService;
import shop.hooking.hooking.service.JwtTokenProvider;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/copy")
public class CopyController {

    private final JwtTokenProvider jwtTokenProvider;

    private final CopyService copyService;

    private final CardRepository cardRepository;

    private final CardJpaRepository cardJpaRepository;


    // 전체 카피라이팅 조회
    @GetMapping("")
    public List<CopyRes> copyList(){
        List<CopyRes> copyRes = copyService.getCopyList();
        Collections.shuffle(copyRes);
        return copyRes;
    }


    // 카피라이팅 검색 조회
    @GetMapping("/search")
    public List<CopyRes> copySearchList(@RequestParam(name = "keyword") String q) {
        if (q.isEmpty()) {
            throw new BadRequestException("검색 결과를 찾을 수 없습니다.");
        }

        MoodType moodType = MoodType.fromKeyword(q);
        if (moodType != null) {
            List<CopyRes> copyRes = copyService.selectMoodByQuery(q);
            Collections.shuffle(copyRes);
            return copyRes;
        } else if (BrandType.containsKeyword(q)) {
            List<CopyRes> copyRes = copyService.selectBrandByQuery(q);
            Collections.shuffle(copyRes);
            return copyRes;
        } else {
            List<CopyRes> copyRes = copyService.selectCopyByQuery(q);
            Collections.shuffle(copyRes);
            return copyRes;
        }
    }




    // 스크랩한 카피라이팅 조회
    @GetMapping("/scrap")
    public List<CopyRes> copyScrapList(HttpServletRequest httpRequest){
        User user = jwtTokenProvider.getUserInfoByToken(httpRequest);
        List<CopyRes> copyRes = copyService.getCopyScrapList(user);
        return copyRes;
    }


    // 카피라이팅 스크랩
    @CrossOrigin(origins = "https://hooking.shop, https://hooking-dev.netlify.app/, https://hooking.netlify.app/, http://localhost:3000, http://localhost:3001")
    @PostMapping("/scrap")
    public HttpRes<String> copyScrap(HttpServletRequest httpRequest, @RequestBody CopyReq copyReq) throws IOException {
        User user = jwtTokenProvider.getUserInfoByToken(httpRequest);
        Card card = cardRepository.findCardById(copyReq.getCardId());
        boolean isScrap = copyService.saveCopy(user, card); // 스크랩됐으면->true, 안됐으면->false
        if(isScrap){
            return new HttpRes<>("스크랩을 완료하였습니다.");
        }

        return new HttpRes<>("스크랩에 실패하였습니다.");
    }


    //카피라이팅 필터
    @GetMapping("/filter")
    public List<CopyRes> searchFilterCard(CardSearchCondition condition) {
        List<CopyRes> results = cardJpaRepository.search(condition);
        Collections.shuffle(results);
        return results;
    }
}
