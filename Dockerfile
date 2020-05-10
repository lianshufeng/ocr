#依赖镜像
FROM registry.cn-chengdu.aliyuncs.com/1s/maven
MAINTAINER lianshufeng <251708339@qq.com>


ARG GIT_URL="https://github.com/lianshufeng/ocr.git"
ARG GIT_NAME="ocr"
ARG FILE_NAME="ocr-1.0-SNAPSHOT.jar"
ARG JAR_FILE="/opt/jar"

#下载源码

RUN set -xe \
	&& source /etc/profile \
	&& cd /tmp/ \
	&& git clone $GIT_URL \
	&& cd /tmp/$GIT_NAME \
	&& mvn package \
	&& mkdir -p $JAR_FILE \
	&& cp /tmp/$GIT_NAME/target/$FILE_NAME $JAR_FILE/$FILE_NAME \
	&& rm -rf /tmp/$GIT_NAME \
	
	#刷新环境变量
	&& source /etc/profile 
	






