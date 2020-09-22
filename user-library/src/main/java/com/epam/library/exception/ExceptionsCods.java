package com.epam.library.exception;

public final class ExceptionsCods
{
    public static final int EVERYTHING_FINE = 200;

    //1** User + Role Errors
    public static final int USER_DOES_NOT_EXIST = 100;

    public static final int ROLE_DOES_NOT_EXIST = 110;

    public static final int USER_ALREADY_BLOCKED = 120;

    public static final int USER_ALREADY_EXIST = 130;

    public static final int WRONG_PASSWORD = 140;

    //3** Input Errors
    public static final int EMPTY_FIELD_INPUT = 300;

    public static final int WRONG_DATE_FORMAT = 301;

    //31* Book Errors
    public static final int BOOK_DOES_NOT_EXIST = 310;

    public static final int PAGE_COUNT_SHOULD_BE_MORE_THEN_ZERO = 311;

    public static final int ISBN_WRONG_FORMAT = 312;

    public static final int BOOK_ALREADY_TAKEN = 313;

    //32* Author Errors
    public static final int AUTHOR_DOES_NOT_EXIST =320;

    //33* Bookmark Errors
    public static final int BOOKMARK_DOES_NOT_EXIST = 330;

    public static final int BOOK_DOSE_NOT_HAVE_SUCH_PAGE = 331;

    //4** Result Errors
    public static final int EMPTY_RESULT = 400;

    public static final int NO_MATCHES_FOUND = 404;

    //500 java exceptions
    public static final int JAVA_EXCEPTIONS = 500;

    //501 db exception
    public static final int ENTITY_CUN_NOT_BE_ADDED_MAX_ID_NOT_RECEIVED = 501;

}
