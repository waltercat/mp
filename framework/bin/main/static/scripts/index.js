/**
 * 首页JavaScript脚本
 * wud
 * 20181021
 */

/**
 * 菜单
 */

/**
 * 当前登录用户
 */

curUser.loadUserFromSession();
$('#userName').text(curUser.userInfo.userName);

curPermission.showPermission();
/**
 * 系统字典数据
 */
