package by.gsu.epamlab.connector.enums;

import by.gsu.epamlab.connector.constant.QueryConstant;

public enum SearchCriteria {
    LEN_GREAT_NUM(QueryConstant.FIND_RECORD);

    private final String query;

    SearchCriteria(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
