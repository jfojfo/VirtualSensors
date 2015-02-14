#include <sys/socket.h>
#include <sys/un.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <pthread.h>


static int socket_local_client_connect(int fd, const char *name)
{
    struct sockaddr_un addr;

    memset(&addr, 0, sizeof(addr));
    addr.sun_family = AF_LOCAL;
    strncpy(addr.sun_path, name, sizeof(addr.sun_path) - 1);

    if (connect(fd, (struct sockaddr *) &addr, sizeof(addr)) < 0) {
        return -1;
    }

    return fd;
}

int socket_local_client(const char *name)
{
    int s;

    if (name == NULL)
        return -1;

    s = socket(AF_LOCAL, SOCK_STREAM, 0);
    if (s < 0)
        return -2;

    if (socket_local_client_connect(s, name) < 0) {
        close(s);
        return -3;
    }

    return s;
}


static inline int64_t get_clock(void)
{
    struct timeval tv;

    gettimeofday(&tv, NULL);
    return tv.tv_sec * 1000000000LL + (tv.tv_usec * 1000);
}

int quit = 0;

void *run(void *arg)
{
    int sock = *((int*) arg);
    char buf[256];
    float v[3];
    while (!quit) {
        v[0] = 10.0 * rand() / (RAND_MAX + 1.0);
        v[1] = 10.0 * rand() / (RAND_MAX + 1.0);
        v[2] = 10.0 * rand() / (RAND_MAX + 1.0);

        int len = sprintf(buf, "acceleration:%g:%g:%g\n", v[0], v[1], v[2]);
        printf("%s", buf);
        if (send(sock, buf, len, 0) < 0) {
            printf("send fail: %s\n", strerror(errno));
        }

        len = sprintf(buf, "orientation:%g:%g:%g\n", v[0], v[1], v[2]);
        printf("%s", buf);
        if (send(sock, buf, len, 0) < 0) {
            printf("send fail: %s\n", strerror(errno));
        }

        len = sprintf(buf, "magnetic:%g:%g:%g\n", v[0], v[1], v[2]);
        printf("%s", buf);
        if (send(sock, buf, len, 0) < 0) {
            printf("send fail: %s\n", strerror(errno));
        }

        len = sprintf(buf, "temperature:%g\n", v[0]);
        printf("%s", buf);
        if (send(sock, buf, len, 0) < 0) {
            printf("send fail: %s\n", strerror(errno));
        }

        len = sprintf(buf, "proximity:%g\n", v[0]);
        printf("%s", buf);
        if (send(sock, buf, len, 0) < 0) {
            printf("send fail: %s\n", strerror(errno));
        }

        len = sprintf(buf, "sync:%lld\n", get_clock() / 1000);
        printf("%s", buf);
        if (send(sock, buf, len, 0) < 0) {
            printf("send fail: %s\n", strerror(errno));
        }

        usleep(50 * 1000);
    }
    return NULL;
}

int main()
{
    int sock = socket_local_client("/data/vsensors");
    if (sock < 0) {
        printf("connect fail\n");
        return -1;
    }
    printf("sock %d\n", sock);

    static pthread_t thread_id;
    pthread_create(&thread_id, NULL, run, &sock);

    while (getchar() != 'q') {}

    quit = 1;
    close(sock);

    return 0;
}
