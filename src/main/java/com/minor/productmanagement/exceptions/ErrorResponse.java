package com.minor.productmanagement.exceptions;

import java.util.List;

public record ErrorResponse(int status, String Response, List<ErrorField> details) {
}
