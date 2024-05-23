package cinema.exception;

public enum ErrorResponseEnum {
    ACCOUNT_NOT_FOUND(404, "Tài khoản không tồn tại"),
    PHONENUMBER_EXISTED(404, "Số điện thoại đã tồn tại"),
    PHONENUMBER_NOT_EXISTED(404, "Số điện thoại không tồn tại"),
    WRONG_PASSWORD(401, "Sai mật khẩu"),
    CINEMA_ROOM_NOT_FOUND(404, "Phòng chiếu phim không tồn tại"),
    FILM_NOT_FOUND(404, "Phim không tồn tại"),

    FOOD_NOT_FOUND(404,"Đồ ăn không tồn tại" ),

    MOVIESHOWTIME_NOT_FOUND(404, "Thời gian chiếu không tồn tại"),

    PROMOTION_NOT_FOUND(404, "Khuyến mãi không tồn tại"),

    REVIEW_NOT_FOUND(404, "Đánh giá không tồn tại"),

    FOOD_TYPE_NOT_FOUND(404, "Thể loại đồ ăn không tồn tại");

    public final int status;
    public final String message;
    ErrorResponseEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
