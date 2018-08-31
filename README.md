# github-socket
This repository as Server for Other program 
作为其他程序的服务端,使用socket通讯通过指定IP+端口接受其他应用的请求并做出响应并返回.
准备用这个项目做成非web项目在linux环境下启动并对外提供服务 
希望用到 spring + maven +  mybatis 
目前使用socket进行通讯将SocketManager作为mian方法进行启动可以完成client的请求,还需要进行spring+mybatis的整合并进行数据库的访问????
整体的一个思路,
		1.工程管理,使用svn或者git进行版本的管理,现没有单独的服务器在用svn时(ip无法固定,虽然svn版本库服务已经搭建好)无法使用
				希望用github,因为github免费且可用,所以需要在linux服务器上安装git(还未安装)
		2.linux服务器上创建并git clone http://www.github.com/liutao1024/github-*.git,项目后需要对项目进行编辑,并得到jar,
				java -jar *****.jar
				java -cp *****.jar cn.spring.mvn.socket.SocketManager 指定Main
		3.使用jenkins对本地仓库的东西进行编译,好像都已经有眉目了(但是出现了web中,依赖的自己的项目base/comm/core/..等找不到)
		
		4.想办法将spring整合到非web项目中


--20180831
			研究linux下安装git  直接通过这个网页  https://www.cnblogs.com/kevingrace/p/8252517.html  安装完成使用最新版本的git


===========================================================安装过程============================================================================
在使用git pull/git push/git clone的时候,或者在使用jenkins发版的时候,可能会报类似如下的错误: 
error: The requested URL returned error: 401 Unauthorized while accessing https://git.oschina.net/zemo/demo.git/info/refs 
fatal: HTTP request failed

这个一般是由于服务器本身自带的git版本过低造成的:

[root@opt SoftWare ~]# git --version
git version 1.7.1
一般只需要将git版本升级到高版本即可。下面说下git升级的操作记录:

安装依赖软件
[root@opt SoftWare ~]# yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel asciidoc
[root@opt SoftWare ~]# yum install  gcc perl-ExtUtils-MakeMaker
 
1)卸载系统自带的底版本git（1.7.1）
[root@opt SoftWare ~]# git --version
git version 1.7.1
[root@opt SoftWare ~]# yum remove git
  
2)编译安装最新的git版本
[root@opt SoftWare ~]# cd /usr/local/src/
[root@opt SoftWare]# wget https://www.kernel.org/pub/software/scm/git/git-2.18.0.tar.xz (此处的版本可以根据需要更改)
[root@opt SoftWare]# tar -vxf git-2.18.0.tar.xz
[root@opt SoftWare]# cd git-2.18.0
[root@opt SoftWare git-2.18.0]# make prefix=/usr/local/git all
[root@opt SoftWare git-2.18.0]# make prefix=/usr/local/git install
[root@opt SoftWare git-2.18.0]# echo "export PATH=$PATH:/usr/local/git/bin" >> /etc/profile (将git/bin添加到环境变量中或者直接vim /etc/profile )
[root@opt SoftWare git-2.18.0]# source /etc/profile (使配置立即生效)
  
[root@opt SoftWare ~]# git --version
git version 2.18.0
  
======================================================================
3)如果是非root用户使用git,则需要配置下该用户下的环境变量
[app@opt SoftWare ~]$ echo "export PATH=$PATH:/usr/local/git/bin" >> ~/.bashrc
[app@opt SoftWare ~]$ source ~/.bashrc
[app@opt SoftWare ~]$ git --version
git version 2.18.0
***************当你发现自己的才华撑不起野心时,就请安静下来学习吧***************
