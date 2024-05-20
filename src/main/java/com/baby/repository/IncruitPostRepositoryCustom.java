package com.baby.repository;

import com.baby.dto.PostSearchDto;
import com.baby.entity.IncruitPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncruitPostRepositoryCustom {
    Page<IncruitPost> getAdminPostPage(PostSearchDto postSearchDto, Pageable pageable);
}
