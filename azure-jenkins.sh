

StatusCode        : 200
StatusDescription : OK
Content           : #!/bin/bash
                    export resourceGroup=kwetter-resources$RANDOM
                    virtualMachine=myVM
                    adminUser=azureuser
                    pathToKubeConfig=~/.kube/config
                    
                    if [ -f $pathToKubeConfig ]
                    then
                    
                        # Create a resource group.
                        az...
RawContent        : HTTP/1.1 200 OK
                    Connection: keep-alive
                    Content-Security-Policy: default-src 'none'; style-src 'unsafe-inline'; sandbox
                    Strict-Transport-Security: max-age=31536000
                    X-Content-Type-Options: nosniff
                    ...
Forms             : {}
Headers           : {[Connection, keep-alive], [Content-Security-Policy, default-src 'none'; style-src 'unsafe-inline'; sandbox], [Strict-Transport-Security, 
                    max-age=31536000], [X-Content-Type-Options, nosniff]...}
Images            : {}
InputFields       : {}
Links             : {}
ParsedHtml        : mshtml.HTMLDocumentClass
RawContentLength  : 2167



