#include <sys/socket.h>
#include <sys/un.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include "common.h"


#define LISTEN_BACKLOG 3


int socket_local_server(const char *name) {
    int fd;
    int sock_reuse;
    struct sockaddr_un serv_addr;
    
    if (name == NULL) {
        return -1;
    }
    
    fd = socket(PF_LOCAL, SOCK_STREAM, 0);
    if (fd == -1)
        return -2;

    memset(&serv_addr, 0, sizeof(serv_addr));
    serv_addr.sun_family = AF_LOCAL;
    strncpy(serv_addr.sun_path, name, sizeof(serv_addr.sun_path) - 1);
    
    /* basically: if this is a filesystem path, unlink first */
    unlink(serv_addr.sun_path);
    
    sock_reuse = 1;
    setsockopt(fd, SOL_SOCKET, SO_REUSEADDR, &sock_reuse, sizeof(sock_reuse));

    if (bind(fd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) == -1) {
        return -3;
    }
    
    if (listen(fd, LISTEN_BACKLOG) == -1) {
        close(fd);
        return -4;
    }
    
    return fd;
}

static int fd_read(int fd, void* buff, int len)
{
    int  len2;
    do {
        len2 = read(fd, buff, len);
    } while (len2 < 0);
    return len2;
}

int channel_recv(int fd, void* buf, int bufsize)
{
    char header[5];
    int size, avail;

    if (fd_read(fd, header, 4) != 4) {
        D("can't read frame header: %s", strerror(errno));
        return -1;
    }
    header[4] = 0;
    if (sscanf(header, "%04x", &size) != 1) {
        D("malformed frame header: '%.*s'", 4, header);
        return -1;
    }
    if (size > bufsize)
        return -1;

    if (fd_read(fd, buf, size) != size) {
        D("can't read frame payload: %s", strerror(errno));
        return -1;
    }
    return size;
}
