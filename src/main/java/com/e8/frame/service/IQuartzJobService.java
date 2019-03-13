package com.e8.frame.service;

import com.e8.frame.model.QuartzJob;
import com.e8.frame.tools.PageUtil;

import java.util.List;

public interface IQuartzJobService {

    List<QuartzJob> queryQuartzJobsByPage(QuartzJob quartzJob, PageUtil page);

    QuartzJob create(QuartzJob resources);

    void updateIsPause(Long id);

    void update(QuartzJob resources);

    void delete(Long id);

    void execution(Long id);
}
