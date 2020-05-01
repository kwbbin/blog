package com.kwbbin.manage.service;


import com.kwbbin.Vo.DraftVo;
import com.kwbbin.bean.Draft;

import java.util.List;

public interface DraftService {
    List<DraftVo> selectAllDraft();
    Draft selectDraftById(Long id);
}
