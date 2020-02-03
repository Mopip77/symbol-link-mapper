package tech.mopip77.symbollinkmapper.exception;

import java.beans.Customizer;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    CONTENT_IS_EMPTY(2001, "输入内容不能为空"),
    HAS_SAME_ITEM(2002, "已有相同配置项"),
    HAS_NOT_THIS_ITEM(2003, "没有该配置项"),
    IS_NOT_A_FOLDER(2004, "输入的路径不是文件夹"),
    HAVE_NO_PERMISSION_IN_FOLDER(2005, "没有文件夹的权限"),
    SOURCE_PATH_NOT_EXIST(2006, "原路径不存在"),
    DEST_PATH_EXIST(2007, "目标路径已存在"),
    DEST_FILE_DELETE_FAIL(2008, "目标文件删除失败"),
    REQUEST_PARAM_RANGE_ERROR(2009, "传入参数范围错误"),
    IS_NOT_ABSOLUTE_PATH(2010, "传入的不是绝对路径"),
    SOURCE_PATH_EQUALS_TO_DEST_PATH(2011, "原路径和目标路径相同"),
    FILE_NAME_NOT_PERMITTED(2012, "传入文件名包含非法字符"),
    SOURCE_PATH_HAS_NO_ORIGIN_PATH(2013, "原文件是软链接但是指向文件丢失"),
    SOURCE_PATH_CANNOT_BE_RECURSIVE_FOLDER(2014, "原路径不能是已经递归创建的目标路径"),
    DELETE_PATH_NOT_EXIST_IN_DELETABLE_PATH(2015, "需要删除的文件夹不存在于可删除路径中"),
    SOURCE_PATH_IS_NOT_FOLDER(2016, "原路径不是文件夹"),
    FOLDER_MAPPER_HAS_ANCESTOR_RELATIONSHIP(2017, "文件夹映射包含祖先关系，会有死循环"),
    SYS_ERROR(5000, "系统运行中出错"),
    ;


    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
