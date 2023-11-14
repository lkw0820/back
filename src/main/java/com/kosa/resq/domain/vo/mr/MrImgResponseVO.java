package com.kosa.resq.domain.vo.mr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MrImgResponseVO {
    private String img_code;
    private String url;
    private String mr_code;
}
