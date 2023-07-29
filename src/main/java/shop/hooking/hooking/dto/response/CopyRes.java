package shop.hooking.hooking.dto.response;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.hooking.hooking.entity.Brand;


import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CopyRes implements Comparable<CopyRes>{
    private Long id;
    private String brandName;
    private String text;
    private Integer scrapCnt;
    private LocalDateTime createdAt;

    private List<Integer> index;

    private LocalDateTime scrapTime;

    @Builder
    @QueryProjection
    public CopyRes(Long id, Brand brand, String text, Integer scrapCnt,LocalDateTime createdAt) {
        this.id = id;
        this.brandName = brand.getBrandName();
        this.text = text;
        this.scrapCnt = scrapCnt;
        this.createdAt = createdAt;
    }

    public CopyRes(Long id, Brand brand, String text, LocalDateTime createdAt) {
        this.id = id;
        this.brandName = brand.getBrandName();
        this.text = text;
        this.scrapCnt = 0;
        this.createdAt = createdAt;
    }

    //왜안됨..?
    @Override
    public int compareTo(CopyRes other) {
        return other.scrapTime.compareTo(this.scrapTime); // 역순으로 정렬
    }


}
