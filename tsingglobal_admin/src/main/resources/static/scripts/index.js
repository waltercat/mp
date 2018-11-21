/**
 * 首页JavaScript脚本
 * wud
 * 20181021
 */

/**
 * 菜单
 */
curPermission.showPermission();
curPermission.loadPage('首页','indexContent.html');
/**
 * 当前登录用户
 */
curUser.loadUserFromSession();
$('#userName').text(curUser.userInfo.userName);


/**
 * 系统字典数据
 */
