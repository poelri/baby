package com.baby.repository;

import com.baby.dto.PostSearchDto;
import com.baby.entity.JobPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobPostRepositoryCustom {
    Page<JobPost> getAdminPostPage(PostSearchDto postSearchDto, Pageable pageable);



}
