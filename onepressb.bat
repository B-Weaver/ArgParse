call gradle clean build jacocoTestReport javadoc
explorer "file:///%~dp0build/reports/tests/index.html"
explorer "file:///%~dp0build/reports/jacoco/test/html/index.html"