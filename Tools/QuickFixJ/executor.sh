#!/bin/sh

scriptdir=`dirname $0`

CP=$scriptdir/jars/mina-core-2.0.7.jar:$scriptdir/jars/mina-filter-compression-2.0.7.jar:$scriptdir/jars/slf4j-api-1.6.3.jar:$scriptdir/jars/slf4j-jdk14-1.6.3.jar:$scriptdir/jars/quickfixj-all-Head.jar:$scriptdir/jars/quickfixj-core-Head.jar:$scriptdir/jars/quickfixj-msg-fix40-Head.jar:$scriptdir/jars/quickfixj-msg-fix41-Head.jar:$scriptdir/jars/quickfixj-msg-fix42-Head.jar:$scriptdir/jars/quickfixj-msg-fix43-Head.jar:$scriptdir/jars/quickfixj-msg-fix44-Head.jar:$scriptdir/jars/quickfixj-msg-fix50-Head.jar:$scriptdir/jars/quickfixj-msg-fixt11-Head.jar:$scriptdir/jars/quickfixj-examples-Head.jar

java -classpath "$CP"  quickfix.examples.executor.Executor $*
