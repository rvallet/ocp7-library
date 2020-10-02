package com.library.mslibrary.enumerated;

public enum SearchCriteriaEnum {

    BY_AUTHOR ("author"),
    BY_TITLE ("title"),
    BY_COLLECTION ("collection");

    private String searchCriteria;

    SearchCriteriaEnum(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public String toString() {
        return searchCriteria;
    }
}
