#!/bin/bash

mkdir -p /var/jenkins_home 
chown -R 1000:1000 /var/jenkins_home #为了宿主机的用户组和容器用户组相同

如果不设置会报错
Can not write to /var/jenkins_home/copy_reference_file.log. Wrong volume permissions?
touch: cannot touch ‘/var/jenkins_home/copy_reference_file.log’: Permission denied

如果设置了还报错
则需要如下操作
那么可以检查一下selinux状态，开启的情况下会导致一些服务安装、使用不成功。
sestatus
临时关闭，
[root@localhost ~]# setenforce 0
永久关闭,可以修改配置文件/etc/selinux/config,将其中SELINUX设置为disabled，如下，
# This file controls the state of SELinux on the system.  
# SELINUX= can take one of these three values:  
#     enforcing - SELinux security policy is enforced.  
#     permissive - SELinux prints warnings instead of enforcing.  
#     disabled - No SELinux policy is loaded.  
#SELINUX=enforcing  
SELINUX=disabled  
# SELINUXTYPE= can take one of three two values:  
#     targeted - Targeted processes are protected,  
#     minimum - Modification of targeted policy. Only selected processes are protected.   
#     mls - Multi Level Security protection.  
SELINUXTYPE=targeted
 
[root@rdo ~]# sestatus  
SELinux status:                 disabled


------------------------------------------------------------

其实只需要
docker-compose.yml
pre_install_jenkins.sh
这两个文件即可

运行命令

# 给脚本授权
chmod u+x *.sh
# 创建Jenkins数据目录（用作持久卷）
./pre_install_jenkins.sh
# 启动Jenkins容器
docker-compose up -d

最后输入
http://[ip]:8080 即可登录

