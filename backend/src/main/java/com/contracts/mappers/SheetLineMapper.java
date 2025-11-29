package com.contracts.mappers;

import com.contracts.MapperUtil;
import com.contracts.dtos.OrderDTO;
import com.contracts.dtos.SheetLineDTO;
import com.domain.MemberOrder;
import com.domain.SheetLine;
import com.domain.Status;

public class SheetLineMapper extends AbsSheetLineMapper<SheetLine, SheetLineDTO> {
    @Override
    public SheetLineDTO createDTO() {
        return new SheetLineDTO();
    }

    @Override
    public SheetLine createEntity() {
        return new SheetLine();
    }
}
