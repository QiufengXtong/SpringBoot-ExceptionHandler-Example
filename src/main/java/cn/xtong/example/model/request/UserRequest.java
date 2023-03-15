package cn.xtong.example.model.request;

import cn.xtong.example.util.Groups;
import cn.xtong.example.util.ListValue;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Data
public class UserRequest {
    @NotNull(message = "ID不能为空", groups = Groups.edit.class)
    private Long id;

    @NotNull(message = "用户名不能为空", groups = Groups.add.class)
    private String username;

    @NotNull(message = "密码不能为空", groups = Groups.add.class)
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码必须为8~16个字母和数字组合", groups = {Groups.add.class})
    private String password;

    @NotNull(message = "性别不能为空", groups = {Groups.add.class, Groups.edit.class})
    @ListValue(message = "性别数据无效", values = {"1", "2"}, groups = {Groups.add.class, Groups.edit.class})
    private String sex;

    @NotNull(message = "邮箱不能为空", groups = {Groups.add.class, Groups.edit.class})
    @Email(message = "邮箱格式不正确", groups = {Groups.add.class, Groups.edit.class})
    private String email;
}
