package shop.hooking.hooking.dto;


import lombok.Builder;
import lombok.Getter;
import shop.hooking.hooking.entity.User;
import java.util.Map;
import shop.hooking.hooking.config.enumtype.Role;


@Getter
public class OAuthAttributesDTO {
    private Map<String, Object> attributes;
    private Long kakaoId;
    private String nickname;
    private String email;
    private String gender;
    private String ageRange;
    private String picture;

    @Builder
    public OAuthAttributesDTO(Map<String, Object> attributes, Long kakaoId, String nickname,
                              String email,String gender,String ageRange, String picture) {
        this.kakaoId = kakaoId;
        this.attributes = attributes;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.ageRange = ageRange;
        this.picture = picture;
    }

    public static OAuthAttributesDTO ofKakao(Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) response.get("profile");

        return OAuthAttributesDTO.builder()
                .kakaoId((Long)attributes.get("id"))
                .nickname((String)profile.get("nickname"))
                .email((String)response.get("email"))
                .gender((String)response.get("gender"))
                .ageRange((String)response.get("age_range"))
                .picture((String)profile.get("profile_image_url"))
                .attributes(attributes)
                .build();
    }

    public User toEntity() {
        User user = User.builder()
                .kakaoId(kakaoId)
                .nickname(nickname)
                .image(picture)
                .email(email)
                .gender(gender)
                .ageRange(ageRange)
                .role(Role.USER)
                .build();

        return user;
    }
}
