def can_convert_to_int(input):
    try:
        int(input)
        return True
    except BaseException:
        return False


def can_convert_to_str(input):
    try:
        str(input)
        return True
    except BaseException:
        return Fals