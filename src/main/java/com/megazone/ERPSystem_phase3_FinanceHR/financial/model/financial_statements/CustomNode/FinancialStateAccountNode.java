package com.megazone.ERPSystem_phase3_FinanceHR.financial.model.financial_statements.CustomNode;

public class FinancialStateAccountNode extends FinancialStateNode {
    public FinancialStateAccountNode(String name, int structureMin) {
        super(name, structureMin);
    }

    @Override
    public void addChild(FinancialStateNode child) {
        // 리프 노드이므로 자식이 없음
    }
}
