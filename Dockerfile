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



#默认的启动命令
ENV ENTRYPOINT="nohup java -Dfile.encoding=UTF-8 -Xmx1000m -Xms600m -Duser.timezone=GMT+8 -jar $JAR_FILE/$FILE_NAME"

#创建启动脚本
RUN set -xe \
	#引导程序
	&& echo "#!/bin/bash" > /opt/bootstrap.sh \
	&& echo "source /etc/profile" >> /opt/bootstrap.sh \
	&& echo "export LANG=en_US.UTF-8" >> /opt/bootstrap.sh \
	&& echo "echo \${ENTRYPOINT}|awk '{run=\$0;system(run)}'" >> /opt/bootstrap.sh 



#安装依赖库
RUN yum --nogpgcheck -y install libstdc++ autoconf automake libtool pkg-config gcc gcc-c++ make libjpeg-devel libpng-devel libtiff-devel zlib-devel 


#安装 leptonica
RUN set -xe \
	&& mkdir -p /tmp/leptonica \
	&& cd /tmp/leptonica \
	&& wget http://www.leptonica.org/source/leptonica-1.79.0.tar.gz \
	&& tar -xvf leptonica-1.79.0.tar.gz \
	&& cd leptonica-1.79.0/ \
	&& ./configure \
	&& make install 


#安装 Tesseract
RUN set -xe \
	&& mkdir -p /tmp/tesseract \
	&& cd /tmp/tesseract \
	&& wget https://github.com/tesseract-ocr/tesseract/archive/4.1.1.zip -O tesseract-4.1.1.zip \
	&& unzip tesseract-4.1.1.zip && mv tesseract-4.1.1 tesseract \
	&& cd tesseract \
	&& ./autogen.sh \
	&& ./configure --with-extra-includes=/usr/local/include --with-extra-libraries=/usr/local/lib \
	&& make install \
	&& ldconfig  




#启动项
ENTRYPOINT  sh /opt/bootstrap.sh 



	






