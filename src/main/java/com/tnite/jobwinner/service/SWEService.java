package com.tnite.jobwinner.service;

import com.tnite.jobwinner.model.SWE;
import java.util.List;
import java.util.UUID;

public interface SWEService {

    boolean save(SWE swe);

    List<SWE> list(SWE swe);

    boolean removeById(UUID id);

    boolean updateById(SWE swe);

    SWE getById(UUID id);
}
