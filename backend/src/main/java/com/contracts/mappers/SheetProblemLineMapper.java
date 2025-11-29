package com.contracts.mappers;

import com.contracts.MapperUtil;
import com.contracts.dtos.OrderDTO;
import com.contracts.dtos.SheetLineDTO;
import com.contracts.dtos.SheetProblemLineDTO;
import com.domain.MemberOrder;
import com.domain.SheetLine;
import com.domain.SheetProblemLine;
import com.domain.Status;

public class SheetProblemLineMapper extends AbsSheetLineMapper<SheetProblemLine, SheetProblemLineDTO> {

    @Override
    public SheetProblemLineDTO createDTO() {
        return new SheetProblemLineDTO();
    }

    @Override
    public SheetProblemLine createEntity() {
        return new SheetProblemLine();
    }
}
