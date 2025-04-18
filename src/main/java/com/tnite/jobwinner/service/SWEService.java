package com.tnite.jobwinner.service;

import com.tnite.jobwinner.model.SWE;
import java.util.List;
import java.util.UUID;

public interface SWEService {

    SWE save(SWE swe);

    // list all
    List<SWE> listAll();

    // list filter
    List<SWE> listFiltered(SWE swe);

    boolean removeById(UUID id);

    boolean updateById(SWE swe);

    // filter
    SWE getById(UUID id);
}
